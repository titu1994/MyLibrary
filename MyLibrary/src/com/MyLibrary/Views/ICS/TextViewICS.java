package com.MyLibrary.Views.ICS;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


public class TextViewICS extends TextView {
	private static final String TAG = "TextViewICS";
	
	private static final String ICS_BLUE_COLOR_STRING = "#33B5E5";
	private static final String ICS_BLUE_COLOR_DARK_STRING = "#0099CC";
	private static final int ICS_BLUE = Color.parseColor(ICS_BLUE_COLOR_STRING);
	private static final int ICS_BLUE_DARK = Color.parseColor(ICS_BLUE_COLOR_DARK_STRING);
	
	private Typeface ICSTypeFace;

	/** While using this class, make sure to include the fonts/.. folder into the assets folder of the 
	 *	project and ics_edittext.xml in the drawable folder.
	 *  
	 *  Optionally, you can add the color xml 'ics_blue' to make the text color ICS Blue.
	 */
	public TextViewICS(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		try{
			ICSTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
			this.setTypeface(ICSTypeFace);
		}catch(Exception e){
			Log.e(TAG, "The asset 'fonts/Roboto-Light.ttf' was not found in the assets folder.", e);
		}
	}
	
	public TextViewICS(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		try{
			ICSTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
			this.setTypeface(ICSTypeFace);
		}catch(Exception e){
			Log.e(TAG, "The asset 'fonts/Roboto-Light.ttf' was not found in the assets folder.", e);
		}
	}
	
	public TextViewICS(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		
		try{
			ICSTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
			this.setTypeface(ICSTypeFace);
		}catch(Exception e){
			Log.e(TAG, "The asset 'fonts/Roboto-Light.ttf' was not found in the assets folder.", e);
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

}
