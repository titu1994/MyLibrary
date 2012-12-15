package com.MyLibrary.Database;


public class UsageOfValues 
{
	
	/*
	onCreate
	private Long mRowId;
	mRowId = (savedInstanceState == null) ? null :
    (Long) savedInstanceState.getSerializable(FoodDBContentProvider.KEY_ROWID);
	
	 onPause and onSaveInstanceState
	private void saveState() {
		String name ;
        String weight ;
        
        ContentValues cv = new ContentValues();
        cv.put(StandardContentProvider.KEY_NAME, name);
        cv.put(StandardContentProvider.KEY_DATA, weight);

        if(mRowId == null){
        	long id = ContentUris.parseId(getContentResolver().insert(StandardContentProvider.DATABASE_URI, cv));
        	if (id > 0) {
        		mRowId = id;
        	}
        }
      
        else{
        getContentResolver().update( Uri.withAppendedPath(StandardContentProvider.DATABASE_URI, String.valueOf(mRowId))
        	, cv, null, null);
        }
        
	}
	
	protected void onSaveInstanceState(Bundle outState) {
		saveState();
		outState.putSerializable(StandardContentProvider.KEY_ROWID, mRowId);
	}
	
	private void populateFields() {
		String[] projection = {StandardContentProvider.KEY_NAME, StandardContentProvider.KEY_DATA};
		if(mRowId != null){
			Cursor food = getContentResolver().query(Uri.withAppendedPath(FoodDBContentProvider.FOOD_URI, String.valueOf(mRowId)),
					projection, null, null, null);
			if(food.moveToFirst()){
			ed8.setText(food.getString(food.getColumnIndexOrThrow(FoodDBContentProvider.KEY_NAME)));
			ed9.setText(food.getString(food.getColumnIndexOrThrow(FoodDBContentProvider.KEY_WEIGHT)));
			}
			food.close();
			
		}
		
	}
	*/
}

