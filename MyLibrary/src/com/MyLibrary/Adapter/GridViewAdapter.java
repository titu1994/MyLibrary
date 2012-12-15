package com.MyLibrary.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.MyLibrary.R;

public class GridViewAdapter extends BaseAdapter {
	private Context context;
	private static double dm;
	private static int buttonSize;
	private static int paddingSize;
	private Button btn[];
	
	public static final String buttonNames[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9","Clear", "0","."};
	
	public GridViewAdapter(Context c, double densityModification){
		context = c;
		dm = densityModification;
	}
	
	@Override
	public int getCount() {
		return buttonNames.length;
	}

	@Override
	public Object getItem(int position) {
		return (Button) btn[position];
	}

	@Override
	public long getItemId(int position) {
		return btn[position].getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		btn = new Button[buttonNames.length];
		
		buttonSize = (int) Math.ceil(50 * dm);
		paddingSize = (int) Math.ceil(8 * dm);
		
		if(convertView == null){
			btn[position] = new Button(context);
			btn[position].setLayoutParams(new GridView.LayoutParams(buttonSize, buttonSize));
			btn[position].setPadding(paddingSize,paddingSize,paddingSize,paddingSize);
		}
		else{
			btn[position] = (Button) convertView;
		}
		
		btn[position].setText(buttonNames[position]);
		btn[position].setBackgroundResource(R.drawable.ics_edittext);
		btn[position].setId(position);
		
		return btn[position];
	}

}
