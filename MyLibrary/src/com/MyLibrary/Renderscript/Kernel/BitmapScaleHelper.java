package com.MyLibrary.Renderscript.Kernel;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class BitmapScaleHelper {
	private Resources mResources;
	private int imgPointer = -1;
	private String imgUri = null;
	
	/**
	 * A decoupled bitmap helper class which can be used to create a smaller memory footprint and prevent 
	 * OutOfMemory exceptions on older devices. Uses the resources of your Activity/Fragment and the resourceID
	 * of the image so as to obtain its dimensions. Does NOT load the image into memory, only its dimensions so you 
	 * must still load the image into memory via some method.
	 * 
	 * @param resources = Use getResources. Decoupled from Activity/Fragment by removing 
	 * 					  Context to reduce memory leaks and reduce memory footprint.
	 * 
	 * @param resourceID = The resource ID of the image. Get via R.-PackageName-.-ImageName-
	 */
	protected BitmapScaleHelper(Resources resources, int resourceID){
		
		mResources = resources;
		imgPointer = resourceID;
	}
	
	protected BitmapScaleHelper(Resources resources, String uri){
		
		mResources = resources;
		imgUri = uri;
	}
	
	/**
	 * Utilise the returned BitmapFactory.Options for a BitmapFactory.decodeResource(.., ..., getScaledBitmapOptions(...));
	 * 
	 * 
	 * @param imageViewWidth = The width of the imageview in which this image will be displayed.
	 * @param imageViewHeight = The height of the imageview in which this image will be displayed.
	 * 
	 * @return BitmapFactory.Options options which is used in a decodeResource method to decode a scaled incoming image.
	 */
	protected BitmapFactory.Options getScaledBitmapOptions(int imageViewWidth, int imageViewHeight){

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		
		if( imgPointer != -1){
			BitmapFactory.decodeResource(mResources, imgPointer, options);
		}
		else {
			BitmapFactory.decodeFile(imgUri, options);
		}
        
	    options.inSampleSize = calculateInSampleSize(options, imageViewWidth, imageViewHeight);
        options.inJustDecodeBounds = false;
        
        return options;
	}
	
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    	// Raw height and width of image
    	final int height = options.outHeight;
    	final int width = options.outWidth;
    	int inSampleSize = 1;

    	if (height > reqHeight || width > reqWidth) {
    		if (width > height) {
    			inSampleSize = Math.round((float)height / (float)reqHeight);
    		} else {
    			inSampleSize = Math.round((float)width / (float)reqWidth);
    		}
    	}
    	return inSampleSize;
    }

}
