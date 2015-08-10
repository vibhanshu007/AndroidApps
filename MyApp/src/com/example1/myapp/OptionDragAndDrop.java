package com.example1.myapp;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OptionDragAndDrop implements OnTouchListener {
	MainActivity mainActivityObject;
	private TextView selectedoptiontextView;
	private ArrayList<String> textOptionIndex;
	private int Xtemp;
	private int Ytemp;
	TextView view;
	RelativeLayout.LayoutParams tempLayoutParams;

	public OptionDragAndDrop(MainActivity mainActivityObject) {
		this.mainActivityObject = mainActivityObject;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		try {
			tempLayoutParams = new RelativeLayout.LayoutParams(
					(int) mainActivityObject.getResources()
					.getDimensionPixelSize(R.dimen.layout_width),
					(int) mainActivityObject.getResources()
					.getDimensionPixelSize(R.dimen.layout_height));

		} catch (Exception e) {

		}

		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			v.bringToFront();
			selectedoptiontextView = null;
			selectedoptiontextView = getCloneOfTextView((TextView) v,
					mainActivityObject);
			Xtemp = (int) event.getRawX() - v.getWidth() / 2;
			Ytemp = (int) event.getRawY() - v.getHeight() / 2;
			tempLayoutParams.leftMargin = Xtemp;
			tempLayoutParams.topMargin = Ytemp;
			selectedoptiontextView.setLayoutParams(tempLayoutParams);
			selectedoptiontextView.setTextSize(mainActivityObject
					.getResources().getDimensionPixelSize(
							R.dimen.optionMovingTextSize));
			break;
		case MotionEvent.ACTION_MOVE:
			selectedoptiontextView.setLayoutParams(tempLayoutParams);
			Xtemp = (int) event.getRawX() - v.getWidth() / 2;
			Ytemp = (int) event.getRawY() - v.getHeight() / 2;

			if (Xtemp > mainActivityObject.getResources()
					.getDimensionPixelSize(R.dimen.window_width)
					- selectedoptiontextView.getWidth() / 2) {
				Xtemp = mainActivityObject.getResources()
						.getDimensionPixelSize(R.dimen.window_width)
						- selectedoptiontextView.getWidth() / 2;
			}
			if (Ytemp > mainActivityObject.getWindowManager()
					.getDefaultDisplay().getHeight()
					- selectedoptiontextView.getHeight() / 2) {
				Ytemp = mainActivityObject.getWindowManager()
						.getDefaultDisplay().getHeight()
						- selectedoptiontextView.getHeight() / 2;
			}
			if (Xtemp < 0) {
				Xtemp = 0;
			}
			if (Ytemp < 0) {
				Ytemp = 0;
			}
			tempLayoutParams.leftMargin = Xtemp;
			tempLayoutParams.topMargin = Ytemp;
			tempLayoutParams.setMargins(Xtemp, Ytemp, 0, 0);
			v.setLayoutParams(tempLayoutParams);

			Log.d("1111111111111", "" + Xtemp + "," + Ytemp);
			break;
		case MotionEvent.ACTION_UP:
			
			v.setBackgroundColor(Color.TRANSPARENT);

			break;

		}
		return true;

	}

	public static TextView getCloneOfTextView(TextView originalView,
			MainActivity applicationObject) {
		TextView cloneView = new TextView(applicationObject);
		if (null != originalView) {
			cloneView.setText(originalView.getText());
			cloneView.setTextSize(originalView.getTextSize());
			cloneView.setTextColor(originalView.getTextColors());
			cloneView.setGravity(originalView.getGravity());
			cloneView.setTypeface(originalView.getTypeface());
			cloneView.setPadding(0, 0, 0, 0);

			getCommonViewAttributes(originalView, cloneView);

		} else {
			cloneView = null;
		}

		return cloneView;
	}

	private static void getCommonViewAttributes(View originalView,
			View cloneView) {
		cloneView.setTag(originalView.getTag());
		cloneView.setId(originalView.getId());

	}

}