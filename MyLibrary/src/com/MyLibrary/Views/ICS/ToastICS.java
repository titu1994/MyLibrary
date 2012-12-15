package com.MyLibrary.Views.ICS;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastICS {
	private Toast t;
	private View v;
	private TextView tv;
	
	private static int length = -1;
	
	public ToastICS(){}

	public ToastICS(LayoutInflater inflater,Toast toast, int ToastLayout_ID,int TextView_ID){
		v = inflater.inflate(ToastLayout_ID, null);
		t = toast;
		tv = (TextView) v.findViewById(TextView_ID);
	}
	
	/**
	 * Directly work as a Toast.
	 * @param inflater - use the getLayoutInflater from an Activity or Fragment
	 * @param toast - Just use " new Toast(this) " for an activity or " new Toast(getSupportActivity) "
	 * @param ToastLayout_ID - Id of the layout which houses the layout of the new Toast. Should be ics_edittext
	 * @param duration - Duration of texts. Should be Toast.LENGTH_LONG or Toast.LENGTH_SHORT
	 * @param TextView_ID - TextView in the Toast. Should be ToastText
	 */
	public ToastICS(LayoutInflater inflater, Toast toast, int ToastLayout_ID, int duration, int TextView_ID){
		v = inflater.inflate(ToastLayout_ID, null);
		t = toast;
		t.setDuration(duration);
		tv = (TextView) v.findViewById(TextView_ID);
		
		length = duration;
	}
	
	public ToastICS(View view, Toast toast, int TextView_ID){
		v = view;
		t = toast;
		tv = (TextView) v.findViewById(TextView_ID);
	}
	
	public ToastICS(View view, Toast toast, int TextView_ID, int duration){
		v = view;
		t = toast;
		t.setDuration(duration);
		tv = (TextView) v.findViewById(TextView_ID);
		
		length = duration;
	}
	
	public Toast getToast(){
		return t;
	}
	
	public View getViewOfToast(){
		return v;
	}
	
	public TextView getTextView(){
		return tv;
	}
	
	public void setToast(Toast toast){
		t = toast;
	}
	
	public void setViewOfToast(View view){
		v = view;
	}
	
	public void setDurationOfToast(int duration){
		length = duration;
	}
	
	public int getDurationOfToast(){
		return length;
	}
	
	public void setTextView(int ID){
		if(v != null && ID != -1){
			tv = (TextView) v.findViewById(ID);
		}
	}
	/** 
	 * Sets both the text to the textview as well as set the view of the toast. This enables abstraction.
	 * @param s
	 */
	public void setToastText(String s){
		tv.setText(s);
		
		if(v != null && t != null){
			t.setView(v);
		}
	}
	/** 
	 * Sets both the text to the textview as well as set the view of the toast. Also asks if dev wants to show text immediately after setting.
	 * Enables Polymorphism, abstraction and general speed.
	 * @param s : Text which must be written
	 * @param showToastImmediately : Boolean for instant showing of toast.
	 */
	public void setToastText(String s, boolean showToastImmediately){
		tv.setText(s);
		
		if(v != null && t != null){
			t.setView(v);
			
			if(showToastImmediately){
				if(length == -1){
					t.setDuration(Toast.LENGTH_SHORT);
					length = Toast.LENGTH_SHORT;
				}
				else
				t.show();
			}
		}
	}
	
	public void showToast(){
		if(t != null || v != null){
			if(length == -1){
				t.setDuration(Toast.LENGTH_SHORT);
				length = Toast.LENGTH_SHORT;
			}
			else
				t.show();
	}
	}
	
}
