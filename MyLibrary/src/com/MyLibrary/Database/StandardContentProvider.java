package com.MyLibrary.Database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/* This is a standard ContentProvider for an Android implementation. To use it just copy paste the entire class into a new 
 * class in your project and then use the comments to add, remove or edit the database.
 */
public class StandardContentProvider extends ContentProvider {
	
	/*	 
	 * These are table ids. Try not to change them. 
	 * Add more ints if sUriMatcher has more than one URI to match. i.e. More tables are required.
	 */
	public static final int TABLE_ID = 1;
	public static final int UNIQUE_TABLE_ID = 2;
	
	/*
	 * These are the coloumns in a database or more than one database. Add more String constants to add more 
	 * coloumns.
	 */
	public static final String KEY_NAME = "name";
	public static final String KEY_DATA= "weight";
	public static final String KEY_ROWID = "_id";
	
	/*
	 * These are string constants for database identifier, database table identifier and the database version.
	 * Name can be changed to whatever you want. 
	 * Table name can be changed. New tables can also be added.
	 * DO NOT CHANGE DATABASE_VERSION.
	 */
	public static final String DATABASE_NAME = "database.db";
	public static final String DATABASE_TABLE = "table";
	public static final int DATABASE_VERSION = 1;
	
	/*
	 * Standard projection for provided coloumns in the standard database provided in the 
	 * standard table. This is a standard. Provide more projection constants if needed.
	 */
	public static final String[] standardProjection = {KEY_ROWID,KEY_NAME,KEY_DATA};
	public static final String[] usedProjection = {KEY_NAME, KEY_DATA};
	
	/*
	 * Standard SQL database creation statement. For more coloumns keep adding +KEY_DATANO. +" text not null, " in required order.
	 * Do not edit last few statements.
	 * 
	 * To create more Tables keep adding DATABASE_CREATE_*NO* , Copy paste the DATABASE_CREATE command and
	 * change the DATABASE_TABLE constant to something else which has been defined near the DB_NAME constant.
	 */
	private static final String DATABASE_CREATE = "create table "+DATABASE_TABLE+" ("+KEY_ROWID+" integer primary key autoincrement, "+KEY_NAME+" text not null, "+KEY_DATA+" text not null);";
	
	/*
	 * Standard Authority. Make sure that you change it to the class path of the copied version.
	 * The basepath is the name of your database table. Add more to be used with the URI below.
	 */
	public static final String AUTHORITY = "com.MyLibrary.Database.StandardContentProvider";
	private static final String BASEPATH = "database";
	
	/*
	 * This is the Uri of your table of your ContentProvider.
	 * To change this change the AUTHORITY and BASEPATH constants above.
	 * To add more tables copy paste the URI and change BASEPATH with something else from above.
	 */
	public static final Uri DATABASE_URI = Uri.parse("content://"+AUTHORITY+"/"+BASEPATH);
	
	/*
	 * These are Content Item Types. Dont understand them. Dont care about them. 
	 * For each new table add the CONTENT_TYPE and just keep changing the names.
	 * Important : Change the names of the constants at the end for your class
	 */
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/Database";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/Database";
	
    /*
     * Uri matcher with two addURI commands for each and every URI defined near DATABASE_URI. 
     * That is your first table. To add more tables add two URI. 
     * Add a new URI and to it change the BASEPATH with something else. After that copy paste the new addURI command
     * and add the + "/#" to the middle argument. Also change the UNIQUE_TABLE_ID to some other constant from above.
     * ContentProvider does not work without these two staying the same.
     */
	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		sUriMatcher.addURI(AUTHORITY, BASEPATH, TABLE_ID);
		sUriMatcher.addURI(AUTHORITY, BASEPATH + "/#" , UNIQUE_TABLE_ID);
	}
	
	/*
	 * Simple global variable to command the actual database.
	 */
	private Database mDbHelper;

	/*
	 * The code which actually handles the entire database. Dont dare change anything.
	 */
	private static class Database extends SQLiteOpenHelper{

		public Database(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		
		/*
		 * To add more tables add more db.execSQL( NEW_DATABASE_CREATE constants);
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
			this.onCreate(db);
		}
		
	}
	
	/*
	 * Initialise your global database command variable. Dont dare change it.
	 */
	@Override
	public boolean onCreate() {
		mDbHelper = new Database(getContext());
		return true;
	}

	/*
	 * Query this database. See internal notes.
	 * 
	 * To add more tables to this one ContentProvider follow the example code in the comments section after this class.
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
			/*
			 * Add tables to be watched over here. Add them through dbQuery.setTables( TableName );
			 */
			SQLiteQueryBuilder dbQuery = new SQLiteQueryBuilder();
			dbQuery.setTables(DATABASE_TABLE);
			
			// Check which URI has been called.
			int uriType = sUriMatcher.match(uri);
			
			switch(uriType){
			// Dont change these. These are very important to remain the same. 
			// Add more case commands to include more IDS for more tables.
			case UNIQUE_TABLE_ID:
				dbQuery.appendWhere(KEY_ROWID + "=" + uri.getLastPathSegment());
				break;
			case TABLE_ID:
				break;
			default:
				throw new IllegalStateException("Unknown URI");
			}
			
			// Cursor to query the database. It is important that it remains the same.
			Cursor mCursor = dbQuery.query(mDbHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
			mCursor.setNotificationUri(getContext().getContentResolver(), uri);
			
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
	}

	@Override
	public String getType(Uri uri) {
		int uriType = sUriMatcher.match(uri);
		
		/*
		 * Add more cases if you want to return additional content types.
		 */
		switch(uriType){
		case TABLE_ID:
			return CONTENT_TYPE;
		case UNIQUE_TABLE_ID:
			return CONTENT_ITEM_TYPE;
		default:
			throw new IllegalStateException("Unknown URI");
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sUriMatcher.match(uri);
		
        if (uriType != TABLE_ID) {
            throw new IllegalArgumentException("Invalid URI for insert");
        }
        
        SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
        /*
         * To add more 
         * To add more tables into this database provide more table constants and add values to them in the content values.
         */
        long newID = sqlDB.insert(DATABASE_TABLE, null, values);
        if (newID > 0) {
            Uri newUri = ContentUris.withAppendedId(uri, newID);
            getContext().getContentResolver().notifyChange(uri, null);
            return newUri;
        } else {
            throw new SQLException("Failed to insert row into " + uri);
        }
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		/*
		 * To delete more tables than the ones provided see the example below to split the task using cases and URIMatcher.
		 */
		int uriType = sUriMatcher.match(uri);
		
        SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
        
        int rowsAffected = 0;
        
        switch (uriType) {
        
        // Delete the entire table provided by DATABASE_TABLE. This is for only this URI and only this database
        // For deleting other tables provide seperate cases matching them to provided ids at the start of the class.
        // See example below for method to do so. DO NOT DELETE more than one table in one case.
        
        case TABLE_ID:
            rowsAffected = sqlDB.delete(DATABASE_TABLE,
                    selection, selectionArgs);
            break;
        case UNIQUE_TABLE_ID:
        	
        	/*
        	 *  Delete a specific coloumn in the table provided by DATABASE TABLE
        	 */
            String id = uri.getLastPathSegment();
            
            if (TextUtils.isEmpty(selection)) {
                rowsAffected = sqlDB.delete(DATABASE_TABLE,
                        KEY_ROWID + "=" + id, null);
            } else {
                rowsAffected = sqlDB.delete(DATABASE_TABLE,
                        selection + " and " + KEY_ROWID + "=" + id,
                        selectionArgs);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown or Invalid URI " + uri);
        }
        
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();

        int rowsAffected;

        /*
         * Updates only a single table based on the URI. For other tables use case to determine which table is being
         * referenced. DO NOT UPDATE more than one table in one case. 
         */
        switch (uriType) {
        case UNIQUE_TABLE_ID:
            String id = uri.getLastPathSegment();
            
            StringBuilder modSelection = new StringBuilder(KEY_ROWID
                    + "=" + id);

            if (!TextUtils.isEmpty(selection)) {
                modSelection.append(" AND " + selection);
            }
            
            /*
             * This updates only one table.
             */
            rowsAffected = sqlDB.update(DATABASE_TABLE,
                    values, modSelection.toString(), null);
            break;
        case TABLE_ID:
            rowsAffected = sqlDB.update(DATABASE_TABLE,
                    values, selection, selectionArgs);
            break;
        default:
            throw new IllegalArgumentException("Unknown URI");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
	}

}

/*
 * For multiple tables. : example of query(...) command
 * public Uri insert(Uri uri, ContentValues values) {
    Uri _uri = null;
    switch (uriMatcher.match(uri)){
    case SAMPLE1:
        long _ID1 = db.insert(
                DATABASE_TABLE1, "", values);

        //---if added successfully---
        if (_ID1>0){
            _uri = ContentUris.withAppendedId(CONTENT_URI1, _ID1);
            getContext().getContentResolver().notifyChange(_uri, null);    
        }
        break;
    case SAMPLE2:
        long _ID2 = db.insert(
                DATABASE_TABLE2, "", values);

        //---if added successfully---
        if (_ID2>0){
            _uri = ContentUris.withAppendedId(CONTENT_URI2, _ID2);
            getContext().getContentResolver().notifyChange(_uri, null);    
        }
        break;
    default: throw new SQLException("Failed to insert row into " + uri);
    }
    return _uri;                
}
 */
