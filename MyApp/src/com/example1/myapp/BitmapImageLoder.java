package com.example1.myapp;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;

public class BitmapImageLoder {
	
	public BitmapDrawable loadImage(String Image){
		BitmapDrawable imageBitmap = new BitmapDrawable(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/MyApp/images/"+Image));
		return imageBitmap;
	}

}
