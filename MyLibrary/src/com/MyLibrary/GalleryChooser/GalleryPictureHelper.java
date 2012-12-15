package com.MyLibrary.GalleryChooser;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class GalleryPictureHelper {
	
	private static final Uri EXTERNAL_PICTURE_URI = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	

	private static final int RESULT_OK = -1;

	
	public static Intent getGalleryIntent(){
		return new Intent(Intent.ACTION_PICK, EXTERNAL_PICTURE_URI);
	}
	
	public static String getUriOfImage(int resultCode, Intent data, Context ctx){
		
		if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = ctx.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            
            ctx = null;
            
            return picturePath;
        }
		else{
			return null;
		}
		
	}

}
