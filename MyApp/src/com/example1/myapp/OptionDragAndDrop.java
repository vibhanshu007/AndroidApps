package com.example1.myapp;

import java.util.ArrayList;



import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.os.Build;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class OptionDragAndDrop implements OnTouchListener {
	MainActivity mainActivityObject;
	private ArrayList<Integer> dragable_id_object_list;
	int getIndexOfObjectTODrag, indexOfCurrentObject;
	boolean isProblemfinished = false;
	private int currentObjects;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint({ "NewApi", "ClickableViewAccessibility" })
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		RelativeLayout.LayoutParams tempLayOutParam = new RelativeLayout.LayoutParams(
				(int) mainActivityObject.getResources().getDimensionPixelSize(
						R.dimen.textview_width), (int) mainActivityObject
						.getResources().getDimensionPixelSize(
								R.dimen.textview_height));
		if (isProblemfinished)
			return false;
		int eventAction = event.getAction();
		
		int X = (int) event.getX();
		int Y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			tempLayOutParam.setMargins(X,Y,0,0);
			v.setLayoutParams(tempLayOutParam);
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return false;
	}
	private int getIndexOfObjectToDrag(int X,int Y){
		MainActivity mainActivityObject;
		int objectIndexToMove = -1;
		for(int dragableIndex : dragable_id_object_list){
		
	}
	return objectIndexToMove;
		
		
	}

}

// =================>>>>> Use For Long Touch <<<<<===================

/*
 * class MyDragListener implements OnDragListener{
 * 
 * @Override public boolean onDrag(View v, DragEvent event) {
 * switch(event.getAction()){ case DragEvent.ACTION_DRAG_STARTED: break; case
 * DragEvent.ACTION_DRAG_EXITED:
 * 
 * break; case DragEvent.ACTION_DRAG_ENTERED: break; case DragEvent.ACTION_DROP:
 * break; case DragEvent.ACTION_DRAG_ENDED: default: break; } return true; } }
 */

