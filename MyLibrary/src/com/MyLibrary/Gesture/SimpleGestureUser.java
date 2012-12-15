package com.MyLibrary.Gesture;

import android.app.Activity;
import android.view.MotionEvent;

import com.MyLibrary.Gesture.SimpleGestureFilter.SimpleGestureListener;

public class SimpleGestureUser implements SimpleGestureListener{
	private SimpleGestureFilter filter;
	private GestureUserInterface gui;
	
	/* Class to help with Gesture events. You must override DispatchMotionEvent(MotionEvent me) and utilize onDispatchTouch
	 * EvenHandler and send the MotionEvent to this function.
	 */
	public SimpleGestureUser(Activity activity){
		filter = new SimpleGestureFilter(activity ,this);
	}
	
	public void onDispatchTouchEventHandler(MotionEvent me){
		filter.onTouchEvent(me);
	}
	
	@Override
	public void onSwipe(int direction) {
		switch (direction) {
		  
		  case SimpleGestureFilter.SWIPE_RIGHT : gui.onSwipeRight();
		                                           break;
		  case SimpleGestureFilter.SWIPE_LEFT :  gui.onSwipeLeft();
		                                                 break;
		  case SimpleGestureFilter.SWIPE_DOWN :  gui.onSwipeDown();
		                                                 break;
		  case SimpleGestureFilter.SWIPE_UP :    gui.onSwipeUp();
		                                                 break;
		  }
	}
	
	@Override
	public void onDoubleTap() {
		gui.onDoubleTap();
	}
	
	public interface GestureUserInterface{
		public void onSwipeRight();
		public void onSwipeLeft();
		public void onSwipeUp();
		public void onSwipeDown();
		public void onDoubleTap();
	}
	
}
