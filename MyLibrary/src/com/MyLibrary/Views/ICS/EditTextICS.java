package com.MyLibrary.Views.ICS;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.MyLibrary.R;

public class EditTextICS extends EditText {
	private static final String TAG = "EditTextICS";
	
	private static final String ICS_BLUE_COLOR_STRING = "#33B5E5";
	private static final String ICS_BLUE_COLOR_DARK_STRING = "#177BBD";
	private static final String ICS_BLUE_COLOR_BRIGHT_STRING = "#38C0F4";
	private static final int ICS_BLUE = Color.parseColor(ICS_BLUE_COLOR_STRING);
	private static final int ICS_BLUE_DARK = Color.parseColor(ICS_BLUE_COLOR_DARK_STRING);
	private static final int ICS_BLUE_BRIGHT = Color.parseColor(ICS_BLUE_COLOR_BRIGHT_STRING);
	
	private static final int VERSION = android.os.Build.VERSION.SDK_INT;
	private static final int ICS = android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;
	
	private Typeface ICSTypeFace;
	
	/** While using this class, make sure to include the fonts/.. folder into the assets folder of the 
	 *	project and ics_edittext.xml in the drawable folder along with the 3 textfield_ .9.png images.
	 *  
	 *  Optionally, you can add the color xml 'ics_blue' to make the text color ICS Blue.
	 */
	
	public EditTextICS(Context context) {
		super(context);
		
		try{
			ICSTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
			this.setTypeface(ICSTypeFace);
		}catch(Exception e){
			Log.e(TAG, "The asset 'fonts/Roboto-Light.ttf' was not found in the assets folder.", e);
		}
		
		if(VERSION < ICS){
			try{
				this.setBackgroundResource(R.drawable.ics_edittext);
			}catch(Exception e){
				Log.e(TAG, "The drawable 'ics_edittext' or its residuals were not found in the drawable sub-folder in res folder.", e);
			}
		}
	}
	public EditTextICS(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		try{
			ICSTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
			this.setTypeface(ICSTypeFace);
		}catch(Exception e){
			Log.e(TAG, "The asset 'fonts/Roboto-Light.ttf' was not found in the assets folder.", e);
		}
		
		if(VERSION < ICS){
			try{
				this.setBackgroundResource(R.drawable.ics_edittext);
			}catch(Exception e){
				Log.e(TAG, "The drawable 'ics_edittext' or its residuals were not found in the drawable sub-folder in res folder.", e);
			}
		}
	}
	
	public EditTextICS(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		try{
			ICSTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
			this.setTypeface(ICSTypeFace);
		}catch(Exception e){
			Log.e(TAG, "The asset 'fonts/Roboto-Light.ttf' was not found in the assets folder.", e);
		}
		
		if(VERSION < ICS){
			try{
				this.setBackgroundResource(R.drawable.ics_edittext);
			}catch(Exception e){
				Log.e(TAG, "The drawable 'ics_edittext' or its residuals were not found in the drawable sub-folder in res folder.", e);
			}
		}
	}
	
	public void setTextColorICS(boolean setBlueICSColor){
		if(setBlueICSColor){
			try{
				this.setTextColor(ICS_BLUE);
			}catch(Exception e){
				Log.e(TAG, "The color 'ics_blue' was not found in the color sub-folder in res folder.", e);
			}
		}
	}
	
	public void setTextColorICSDark(boolean setBlueICSColorDark){
		if(setBlueICSColorDark){
			try{
				this.setTextColor(ICS_BLUE_DARK);
			}catch(Exception e){
				Log.d(TAG, "The color 'ics_blue' was not found in the color sub-folder in res folder.", e);
			}
		}
	}
	
	public void setTextColorICSBright(boolean setBlueICSColorBright){
		if(setBlueICSColorBright){
			try{
				this.setTextColor(ICS_BLUE_BRIGHT);
			}catch(Exception e){
				Log.d(TAG, "The color 'ics_blue' was not found in the color sub-folder in res folder.", e);
			}
		}
	}

}
