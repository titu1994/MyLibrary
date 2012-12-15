package com.MyLibrary.ActionModes;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class ListViewContextualActionMode implements ActionMode.Callback{
	private ListView lv;
	
	private static OnItemLongClickListener longClickListener;
	private static OnItemClickListener clickListener;
	
	
	/**
	 * When initialising this class, initialise it inside a listview.OnItemLongClickListener and make sure to implement 
	 * the following lines of code.
	 * 
	 * 1) getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	 * 2) implement getListView().setOnItemLongClickListener(new OnItemLongClickListener(){...}) 
	 * 	  and in the OnItemLongClick(...) method, take whatever action needed and then call the SherlockActionBar
	 * 	  as the second last line before "return true".
	 * 
	 * 	  getSherlockActivity().startActionMode(new ListviewContextualActionMode(getListView(), position);
	 * 
	 * @param listview = The listview on which this actionmode will be called to show the ContextualActionBar
	 * @param position = The position of the item "Long-Clicked" obtained from OnItemLongClick
	 */
	public ListViewContextualActionMode(ListView listview, int position){
		super();
		
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listview.setItemChecked(position, true);
		
		lv = listview;
		clickListener = lv.getOnItemClickListener();
		longClickListener = lv.getOnItemLongClickListener();
		
		if(clickListener == null){
			clickListener = new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					
				}
			};
		}
		
		if(longClickListener == null){
			longClickListener = new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					return true;
				}
			};
		}
	}
	
	/**
	 * Change Whatever you require in this method but leave the subtitles unless required to show something else.
	 */
	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		onCreateActionModeActions(mode, menu);
		
		/*
		 * Edit method from here, however required.
		 */
		
		return true;
	}

	private void onCreateActionModeActions(final ActionMode mode, Menu menu) {		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				SparseBooleanArray ba = lv.getCheckedItemPositions();
				
				if(ba.get(position)){
					lv.setItemChecked(position, false);
				}
				else{
					lv.setItemChecked(position, true);
				}
				
				int ids = lv.getCheckedItemCount();
				
				mode.setSubtitle(ids +" items are checked.");
			}
		});

		int ids = lv.getCheckedItemCount();
		mode.setSubtitle(ids + " items are checked.");
		
	}

	
	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		
		return false;
	}
	
	/**
	 * Edit this method to however you require and look at convenience method to implement the onActionItemClicked method.
	 *			
		 		for(int i=0; i<noOfListItems;i++){
					if(ba.get(i)){
						long id = lv.getItemIdAtPosition(i);
						
						Uri u = ContentUris.withAppendedId(DATABASE_URI, id);
						getSherlockActivity().getContentResolver().delete(u, null, null);
					}
				}
				mode.setSubtitle(0+ " items are checked.");
	 */
	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		
		//int noOfListItems = lv.getCount();
		//SparseBooleanArray ba = lv.getCheckedItemPositions();
		
		switch(item.getItemId()){
		
			// Edit cases here.
		
			default:
			{
				mode.setSubtitle(lv.getCheckedItemCount()+ " items are checked.");
				return false;
			}
		}

	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		
		if(lv != null){
			lv.setOnItemClickListener(clickListener);
			lv.setOnItemLongClickListener(longClickListener);
		}
		
		CheckedItemReset(false);
		
		// To stop holding onto the context and release memory.
		lv = null;
	}
	
	private void CheckedItemReset(boolean toCheck) {
		if(lv != null){
			int checked = lv.getCount();
			SparseBooleanArray ba = lv.getCheckedItemPositions();
			
			for(int i = 0; i< checked; i++){
					if(ba.get(i)){
						if(lv != null)
							lv.setItemChecked(i, toCheck);
					}
				}
		}
	}


}
