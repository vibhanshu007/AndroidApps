package com.example1.myapp;

import java.util.ArrayList;
import java.util.HashMap;


import android.widget.TextView;

public class MathingAnswer {
	MainActivity mainActivity;
	HashMap<TextView, String> placeHolder,checkRepeatedQuestionValues;
	BitmapImageLoder bitmapImageLoder;
	HashMap<String,String> allListOfAnswer;
	private ArrayList<Integer> repeatQuestion;
	int[] pointIdX={R.string.pointX1,R.string.pointX2,R.string.pointX3,R.string.pointX4};
	int[] pointIdY={R.string.pointY1,R.string.pointY2,R.string.pointY3,R.string.pointY4};
	
	public MathingAnswer(MainActivity mainActivity) {
		this.mainActivity=mainActivity;
		allListOfAnswer = new HashMap<String,String>();
	}
	
	

}
