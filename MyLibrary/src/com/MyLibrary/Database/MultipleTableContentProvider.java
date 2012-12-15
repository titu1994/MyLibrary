package com.MyLibrary.Database;

import java.util.Arrays;

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

public class MultipleTableContentProvider extends ContentProvider {

	/* 
	 * TODO Document the use of more TABLE_IDS and UNIQUE_TABLE_IDS. They should be symbiotic and unique.
	 */
	public static final int[] TABLE_IDS = {/* Unique identifiers pertaining to no. of tables. */};
	
	public static int[] UNIQUE_TABLE_IDS = new int[TABLE_IDS.length];
	static{
		Arrays.sort(TABLE_IDS);
		
		int noOfIDS = TABLE_IDS.length;
		
		for(int i = 0; i<noOfIDS; i++){
			UNIQUE_TABLE_IDS[i] = -1 * TABLE_IDS[i];
		}
		
		Arrays.sort(UNIQUE_TABLE_IDS);
	}
	
	/*
	 * TODO Document to add more String variables for keys and add more String arrays for holding different keys 
	 * of different tables
	 */
	public static final String KEY_ROWID = "_id";
	
	public static final String KEY_NAME = "name";
	public static final String KEY_DATA= "weight";
	
	
	
	/*
	 * Remember to make them private and name them according to this convention. 
	 * These arrays MUST have KEY_ROWID as their starting element or the SQLCommandsCreator class returns an exception.
	 * Reflect these values in the public field standardProjectionTable'n'. 
	 * Provide more blocks of data if required.
	 * eg.
	 * private static final String[][] KEY_TABLES = {{KEY..}, {KEY...}, {KEY....}};
	 */
	private static final String[][] KEY_TABLES = { {KEY_ROWID, KEY_NAME, KEY_DATA}
													/* Remember to add more {} after a comma here for
													 * more tables and the respective keys.*/ };
	
	
	
	/*
	 * You need to put the exact number of table names as in the length of KEY_TABLES has 1st dimensions.
	 * Else it will give an array out of bounds exception.
	 * TODO Document the adition of mroe valus to DATABASE_TABLES to create more tables.
	 * DO NOT CHANGE DATABASE_VERSION.
	 */
	public static final String DATABASE_NAME = "database.db";
	public static final String[] DATABASE_TABLES = {"table"};
	public static final int DATABASE_VERSION = 1;
	
	/*
	 * TODO Document the addition of more standardProjection and usedProjection for other tables.
	 * Standard projection for provided coloumns in the standard database provided in the 
	 * standard table. This is a standard. Provide more projection constants if needed.
	 */
	public static final String[] standardProjectionTable1 = KEY_TABLES[0];
	
	
	
	
	/*
	 * Create more TABLE_CREATE'n' commands where n is Table no and submit table names as DATABASE_TABLES[0,1,2..] for each
	 * table to remain efficient and exhaustive. Also submit KEY_TABLE'n' as the second argument.
	 */
	private static final String[] DATABASE_TABLE_CREATE = new String[KEY_TABLES.length];
	static{
		int noOfTables = KEY_TABLES.length;
		
		for(int i=0; i<noOfTables; i++){
			DATABASE_TABLE_CREATE[i] = SQLCommandsCreator.databaseCreateCommand(DATABASE_TABLES[i], KEY_TABLES[i]);
		}
	}
	
	/*
	 * Standard Authority. Make sure that you change it to the class path of the copied version.
	 * The basepath is the name of your database table.
	 */
	public static final String AUTHORITY = "com.MyLibrary.Database.StandardContentProvider";
	private static final String[] BASEPATHS = DATABASE_TABLES;
	
	/*
	 * This is the Uri of your table of your ContentProvider.
	 * Document it properly.
	 * 
	 * To add more tables copy paste the URI and change BASEPATH[0] with BASEPATH[n] from above where n is between 0 
	 * (BASEPATHS.length-1).
	 */
	
	/**	Document the respective URIS that this content provider will expose.
	 *	DATABASE_URIS = 0 for ... 
	 * 				  = 1 for ...
	 */
	public static Uri[] DATABASE_URIS = new Uri[BASEPATHS.length];
	static{
		int noOfTables = BASEPATHS.length;
		
		for(int i=0; i<noOfTables; i++){
			DATABASE_URIS[i] = Uri.parse("content://"+AUTHORITY+"/"+BASEPATHS[i].trim());
		}
	}
	
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
		int noOfTables = BASEPATHS.length;
		
		for(int i = 0; i < noOfTables; i++){
			sUriMatcher.addURI(AUTHORITY, BASEPATHS[i], TABLE_IDS[i]);
			sUriMatcher.addURI(AUTHORITY, BASEPATHS[i] + "/#" , UNIQUE_TABLE_IDS[i]);
		}
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
		
		/**
		 * To add more tables add more db.execSQL( DATABASE_TABLE_CREATE'n');
		 * 
		 * Make sure to do this or this class will give huge numbers of errors.
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			int noOfTables = KEY_TABLES.length;
			
			for(int i=0; i<noOfTables; i++){
				db.execSQL(DATABASE_TABLE_CREATE[i]);
			}
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			int noOfTables = DATABASE_TABLES.length;
			
			for(int i=0; i<noOfTables; i++){
				db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLES[i]);
			}
			
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
	public Cursor query(Uri uri, String[] projection, String selection,	String[] selectionArgs, String sortOrder) {
		
			int noOfTables = DATABASE_TABLES.length;
			/*
			 * Add tables to be watched over here. Add them through dbQuery.setTables( TableName );
			 */
			SQLiteQueryBuilder[] dbQuery = new SQLiteQueryBuilder[noOfTables];
			
			for(int i = 0; i<noOfTables; i++){
				dbQuery[i] = new SQLiteQueryBuilder();
				dbQuery[i].setTables(DATABASE_TABLES[i]);
			}
			
			Cursor mCursor;
			
			// Check which URI has been called.
			int uriType = sUriMatcher.match(uri);
			
			int tablePosition = Arrays.binarySearch(TABLE_IDS, uriType);
			int uniqueTablePosition = 0;
			
			if(tablePosition < 0){
				uniqueTablePosition = Arrays.binarySearch(UNIQUE_TABLE_IDS, uriType);
				
				if(uniqueTablePosition < 0){
					throw new IllegalStateException("URI" + uri.toString() +" does not exist.");
				}
				else{
					dbQuery[uniqueTablePosition].appendWhere(KEY_ROWID + "=" + uri.getLastPathSegment());
					
					tablePosition = uniqueTablePosition;
				}
			}
			
			// Cursor to query the database. It is important that it remains the same.
			mCursor = dbQuery[tablePosition].query(mDbHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
			mCursor.setNotificationUri(getContext().getContentResolver(), uri);
			
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
	}

	@Override
	public String getType(Uri uri) {
		int uriType = sUriMatcher.match(uri);
		
		int tablePosition = Arrays.binarySearch(TABLE_IDS, uriType);
		int uniqueTablePosition = 0;
		
		if(tablePosition < 0){
			uniqueTablePosition = Arrays.binarySearch(UNIQUE_TABLE_IDS, uriType);
			
			if(uniqueTablePosition >= 0){
				return CONTENT_ITEM_TYPE;
			}
			else{
				throw new IllegalStateException("URI" + uri.toString() +" does not exist.");
			}
		}
		else{
			return CONTENT_TYPE;
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sUriMatcher.match(uri);
		
		int tablePosition = Arrays.binarySearch(TABLE_IDS, uriType);
		int uniqueTablePosition = 0;
		
		if(tablePosition < 0){
			uniqueTablePosition = Arrays.binarySearch(UNIQUE_TABLE_IDS, uriType);
			
			if(uniqueTablePosition < 0){
				throw new IllegalArgumentException("URI" + uri.toString() +" does not exist and hence cannot be inserted to.");
			}
			else{
				tablePosition = uniqueTablePosition;
			}
		}
		
        
        SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
        
        long newID = sqlDB.insert(DATABASE_TABLES[tablePosition], null, values);
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
        int tablePosition = Arrays.binarySearch(TABLE_IDS, uriType);
		int uniqueTablePosition = 0;
		
		if(tablePosition < 0){
			uniqueTablePosition = Arrays.binarySearch(UNIQUE_TABLE_IDS, uriType);
			
			if(uniqueTablePosition < 0){
				throw new IllegalArgumentException("URI" + uri.toString() +" does not exist or is invalid.");
			}
			else{
				String id = uri.getLastPathSegment();
	            
	            if (TextUtils.isEmpty(selection)) {
	                rowsAffected = sqlDB.delete(DATABASE_TABLES[uniqueTablePosition],
	                        KEY_ROWID + "=" + id, null);
	            } else {
	                rowsAffected = sqlDB.delete(DATABASE_TABLES[uniqueTablePosition],
	                        selection + " and " + KEY_ROWID + "=" + id,
	                        selectionArgs);
	            }
			}
		}
		else{
			rowsAffected = sqlDB.delete(DATABASE_TABLES[tablePosition],
                    selection, selectionArgs);
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
        int tablePosition = Arrays.binarySearch(TABLE_IDS, uriType);
		int uniqueTablePosition = 0;
		
		if(tablePosition < 0){
			uniqueTablePosition = Arrays.binarySearch(UNIQUE_TABLE_IDS, uriType);
			
			if(uniqueTablePosition < 0){
				throw new IllegalStateException("URI" + uri.toString() +" does not exist.");
			}
			else{
				String id = uri.getLastPathSegment();
	            
	            StringBuilder modSelection = new StringBuilder(KEY_ROWID
	                    + "=" + id);

	            if (!TextUtils.isEmpty(selection)) {
	                modSelection.append(" AND " + selection);
	            }
	            rowsAffected = sqlDB.update(DATABASE_TABLES[uniqueTablePosition],
	                    values, modSelection.toString(), null);
			}
		}
		else{
			rowsAffected = sqlDB.update(DATABASE_TABLES[tablePosition],
                    values, selection, selectionArgs);
		}
        
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
	}
}
