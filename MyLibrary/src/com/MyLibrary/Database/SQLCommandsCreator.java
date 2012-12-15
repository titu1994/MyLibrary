package com.MyLibrary.Database;

public class SQLCommandsCreator {
	
	private static final String KEY_ROWID = "_id";
	
	/**
	 * This function creates a new SQL command string which can be used in db.execSQL(string) to create a new table.
	 * All coloumns of this table will be of String type. Convert everything to string to use this table.
	 * 
	 * @param tableName - A table name which is a constant in the ContentProviderClass.
	 * @param coloumnKeys - A string array which will be a projection of the table. MUST have KEY_ROWID = "_id" at the start of the array.
	 * @return String variable which can be used in a db.execSQL(string) command to create a table.
	 * 
	 * Throws an IllegalArgumentException if it does not find KEY_ROWID at the start of the array coloumnKeys.
	 */
	public static String databaseCreateCommand(String tableName, String... coloumnKeys){
		String command = null;
		command = "create table "+tableName.trim()+" (";
		
		int noOfKeys = coloumnKeys.length;
		
		if(!coloumnKeys[0].trim().equals(KEY_ROWID)){
			throw new IllegalArgumentException("KEY_ROWID = _id not found at start of coloumnKeys.");
		}
		else{
			command = command + KEY_ROWID +" integer primary key autoincrement, ";
		}
		
		for(int i = 1; i < noOfKeys; i++){
			if(i == (noOfKeys-1)){
				command = command + coloumnKeys[i].trim() +" text not null);";
			}
			else{
				command = command + coloumnKeys[i].trim() +" text not null,";
			}
		}
		
		return command;
	}

}
