package com.MyLibrary.FileIO;

import java.io.IOException;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

public class WriteDataToFile extends AsyncTask<String, Void, Void> implements LoaderManager.LoaderCallbacks<Cursor>{	
	// TODO Set up the projection of your ContentProvider.
	private String[] projection = {"Replace with KEY_ID and then continue with other KEY_.."};
	
	// TODO File name list.
	private String[] fileNames;
	
	// TODO Set up the folder path.
	private String folderPath = "com.";
	
	/** 
	 * Set up more parts for more Tags and add them later to the LoaderManager.
	 * Set up your Database URI. By databaseURI = mContentProvider.DATABASE_URI
	 */
	private Uri databaseURI ;
	private int cursorTAG = 0x01;
	private Cursor DataCursor = null;
	
	private FragmentActivity act;
	
	/**
	 * Change the value of the Fragment Activity to SherlockFragment or SherlockFragmentActivity or better yet 
	 * subclass this ASyncTask. 
	 * @param FragmentActivity a
	 */
	public WriteDataToFile(FragmentActivity a){
		/*
		 * Add things that you need to initialise here.
		 */
		act = a;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		/**
		 * Usually set up a progressContainer to show a progress.
		 * progressContainer.setVisibility(View.VISIBLE);
		 * 
		 * Remove this line if you subclass this asynctask and replace with adequate loaderManager call.
		 */
			act.getSupportLoaderManager().initLoader(cursorTAG, null, this);
		
	}

	@Override
	protected Void doInBackground(String... params) {
		WriteFile wf = null;
		int noOfFiles = params.length;
		int noOfProjections = projection.length;
		
		if(!isCancelled()){
			fileNames = new String[noOfFiles];
			
			for(int i = 0; i< noOfFiles; i++){
				fileNames[i] = params[i];
			}
			
			wf = new WriteFile(folderPath, fileNames);
			
			int count = DataCursor.getCount();
			
			if(count != 0 || count >= 1){
			if(DataCursor.moveToFirst()){
				for(int j = 0; j< count; j++){
					if(!isCancelled()){
						try{
							/**
							 * Replace the calls here with the data from your own. 
							 * 
							 * Things to replace :
							 * @param projection[0] with required projection call or individual call to your ContentProvider.
							 * @param Log1 with fileName[0] and similarly for wf.write----
							 * @param wf.writeString with required wf.write---- and DataCursor.getString to DataCursor.get--- in last line.
							 */
							for(int k = 0; k < noOfFiles; k++){
								for(int u = 1; u < noOfProjections; u++){
									if(!DataCursor.isNull(DataCursor.getColumnIndexOrThrow(projection[u])))
										wf.writeString(fileNames[k], DataCursor.getString(DataCursor.getColumnIndexOrThrow(projection[u])));
								}
							}
							
						}catch(IOException e){
							e.printStackTrace();
						}
						
						DataCursor.moveToNext();
					}
					else{
						break;
					}
				}
			}
			}
			}
			
		wf.closeBuffers();
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		
		/**
		 * Always end the progressContainer set up in onPreExecute.
		 * 
		 * if(progressContainer.isEnabled())
				progressContainer.setVisibility(View.GONE);
		 */
		
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		
		/**
		 * Change the addressing here by making this a subclassed ASyncTask to remove the requirement of act.
		 */
		if(act != null){
			/**
			 * Destroy as many loaders as required.
			 */
			if(act.getSupportLoaderManager().hasRunningLoaders()){
					act.getSupportLoaderManager().destroyLoader(cursorTAG);
			}
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int cursorTag, Bundle arg1) {
		/**
		 * Change the act in CursorLoader with Appropriate getSherlockActivity() or getActivity(). Do this by subclassing.
		 * 
		 * Add more cursorTAGs[1,2,...] till you reach required number of cursors. 
		 * 
		 * Important note : You can only use one URI and therefore are limited to only one database. 
		 * Multiple Table management of tables is not supported. Therefore use multiple cursors only for 
		 * creating a structured approach to data management. It is not recommended to use several as they all get the same job done. :/
		 */
		
		//TODO change act to getActivity() or getSherlockActivity() by subclassing this class.
		CursorLoader cl = new CursorLoader(act, databaseURI, projection, null, null, null);
		return cl;
		
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		DataCursor = arg1;
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		
	}

}
