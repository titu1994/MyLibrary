package com.MyLibrary.Views.ICS;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import com.MyLibrary.R;

public class ButtonICS extends Button {
	private static final String TAG = "ButtonICS";
	
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
	 * 	project. Also include the following xmls into the drawable folder of your project.
	 */
	public ButtonICS(Context context) {
		super(context);
		
		try{
			ICSTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
			this.setTypeface(ICSTypeFace);
		}catch(Exception e){
			Log.e(TAG, "The asset 'fonts/Roboto-Light.ttf' was not found in the assets folder.", e);
		}
		
		if(VERSION < ICS){
			try{
				this.setBackgroundResource(R.drawable.buttonimage);
			}catch(Exception e){
				Log.e(TAG, "The drawable 'buttonimage' and its residuals were not found in the drawable subfolder of the res folder.", e);
			}
		}
	}
	
	public ButtonICS(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		try{
			ICSTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
			this.setTypeface(ICSTypeFace);
		}catch(Exception e){
			Log.e(TAG, "The asset 'fonts/Roboto-Light.ttf' was not found in the assets folder.", e);
		}
		
		if(VERSION < ICS){
			try{
				this.setBackgroundResource(R.drawable.buttonimage);
			}catch(Exception e){
				Log.e(TAG, "The drawable 'buttonimage' and its residuals were not found in the drawable subfolder of the res folder.", e);
			}
		}
	}
	
	public ButtonICS(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		try{
			ICSTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
			this.setTypeface(ICSTypeFace);
		}catch(Exception e){
			Log.e(TAG, "The asset 'fonts/Roboto-Light.ttf' was not found in the assets folder.", e);
		}
		
		if(VERSION < ICS){
			try{
				this.setBackgroundResource(R.drawable.buttonimage);
			}catch(Exception e){
				Log.e(TAG, "The drawable 'buttonimage' and its residuals were not found in the drawable subfolder of the res folder.", e);
			}
		}
	}

	public void setTextColorICS(boolean setBlueICSColor){
		if(setBlueICSColor){
			try{
				this.setTextColor(ICS_BLUE);
			}catch(Exception e){
				Log.d(TAG, "The color 'ics_blue' was not found in the color sub-folder in res folder.", e);
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
