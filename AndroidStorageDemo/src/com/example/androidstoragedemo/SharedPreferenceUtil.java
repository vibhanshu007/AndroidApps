package com.example.androidstoragedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceUtil {
	public static final String PREFRENCE_NAME= "VIBS_PREFRENCE";
	public static final String PREFRENCE_VALUE= "VIBS_PREFRENCE_String ";
	
	public void SharedPreference(){
		
	}
	public void save(Context context,String text) {
	SharedPreferenceUtil setting;
	Editor editor;
	
	setting=(SharedPreferenceUtil)context.getSharedPreferences(PREFRENCE_NAME, context.MODE_PRIVATE);
	
	}
}
