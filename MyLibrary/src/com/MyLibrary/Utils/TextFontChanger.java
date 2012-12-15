package com.MyLibrary.Utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextFontChanger {
	private static Typeface tf;
	
	public static void setTypeface(AssetManager AssetMngr){
		tf = Typeface.createFromAsset(AssetMngr , "fonts/Roboto-Light.ttf");
	}
	
	public static void changeFont(TextView...TA){
		int length = TA.length;
		
		for(int i = 0; i < length; i++){
			TA[i].setTypeface(tf);
		}
	}
	
	public static void changeFont(EditText...ED){
		int length = ED.length;
		
		for(int i = 0; i < length; i++){
			ED[i].setTypeface(tf);
		}
	}
	
	public static void changeFont(Button...BU){
		int length = BU.length;
		
		for(int i = 0; i < length; i++){
			BU[i].setTypeface(tf);
		}
	}
	
	public static void changeFont(TextView t, EditText e, Button b){
		t.setTypeface(tf);
		e.setTypeface(tf);
		b.setTypeface(tf);
	}
	
	public static void changeFont(TextView[] TA, EditText[] ED, Button[] BU){
		int lengthTA = TA.length;
		int lengthED = ED.length;
		int lengthBU = BU.length;
		
		for(int i = 0; i < lengthTA; i++){
			if(TA[i] != null)
			TA[i].setTypeface(tf);
		}
		
		for(int i = 0; i < lengthED; i++){
			if(ED[i] != null)
			ED[i].setTypeface(tf);
		}
		
		for(int i = 0; i < lengthBU; i++)
		{
			if(BU[i] != null)
			BU[i].setTypeface(tf);
		}
	}
}
