package com.MyLibrary.GridAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.MyLibrary.R;

public class NumPadAdapter extends BaseAdapter {
	private Context context;
	private static double dm;
	private static int buttonSize;
	private static int paddingSize;
	
	public static final String buttonNames[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9","Clear", "0","."};
	
	public NumPadAdapter(Context c, double densityModification){
		context = c;
		dm = densityModification;
	}
	
	@Override
	public int getCount() {
		return buttonNames.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Button btn;
		buttonSize = (int) Math.ceil(70 * dm);
		paddingSize = (int) Math.ceil(8 * dm);
		
		if(convertView == null){
			btn = new Button(context);
			btn.setLayoutParams(new GridView.LayoutParams(buttonSize, buttonSize));
			btn.setPadding(paddingSize,paddingSize,paddingSize,paddingSize);
		}
		else{
			btn = (Button) convertView;
		}
		
		btn.setText(buttonNames[position]);
		btn.setBackgroundResource(R.drawable.ics_edittext);
		btn.setId(position);
		
		return btn;
	}
	
	/* How to use :
	 * 
	 * 
	 * gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
	 */

}
