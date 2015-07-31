package com.example1.myapp;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ArrayList<String> imageList, textList;
	// private int currentDrawable;
	BitmapImageLoder bitImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bitImage = new BitmapImageLoder();
		imageList = new ArrayList<String>();
		textList = new ArrayList<String>();
		getImageName();
		//getTextView();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			String text = "clicked at X" + event.getX() + "clicked at Y"
					+ event.getX();
			Toast.makeText(this, text, Toast.LENGTH_LONG).show();

			System.out.println((int) event.getX() + "," + (int) event.getY());
			Log.v("IN DP --->", (int) event.getX() / 1.5 + "" + ","
					+ (int) event.getY() / 1.5);
		}
		return super.onTouchEvent(event);

	}

	@Override
	protected void onStart() {

		Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.move);
		TextView heading = (TextView) findViewById(R.id.question_textview);
		heading.startAnimation(anim1);

		Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.leftslide_textview);
		TextView answer1 = (TextView) findViewById(R.id.textview1);
		TextView answer2 = (TextView) findViewById(R.id.textview2);
		answer1.startAnimation(anim2);
		answer2.startAnimation(anim2);

		Animation anim3 = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.rigthslide_textview);
		TextView answer3 = (TextView) findViewById(R.id.textview3);
		TextView answer4 = (TextView) findViewById(R.id.textview4);
		answer3.startAnimation(anim3);
		answer4.startAnimation(anim3);
		super.onStart();
	}

	/*
	 * private int[] questionImages = new int[] { R.drawable.bee_1,
	 * R.drawable.bird_1, R.drawable.calve_1, R.drawable.chicken_5 };
	 */
	public void getImageName() {
		ImageView image1 = (ImageView) findViewById(R.id.imageview1);
		/*
		 * currentDrawable = (int) (Math.random() * ((questionImages.length -
		 * 1)+1)); image1.setImageResource(questionImages[currentDrawable]);
		 */
		ImageView image2 = (ImageView) findViewById(R.id.imageview2);
		ImageView image3 = (ImageView) findViewById(R.id.imageview3);
		ImageView image4 = (ImageView) findViewById(R.id.imageview4);

		File imageFile = new File(Environment.getExternalStorageDirectory()
				.getPath() + "/MyApp/images/");
		String[] s = imageFile.list();
		for (int i = 0; i < s.length; i++) {
			imageList.add(s[i]);
		}
		Collections.shuffle(imageList);
		image1.setImageDrawable(bitImage.loadImage(imageList.get(0)));
		image2.setImageDrawable(bitImage.loadImage(imageList.get(1)));
		image3.setImageDrawable(bitImage.loadImage(imageList.get(2)));
		image4.setImageDrawable(bitImage.loadImage(imageList.get(3)));
		Log.e("imageList", "" + imageList.size());

	}

	/*public void getTextView() {
		TextView textView1 = (TextView) findViewById(R.id.textview1);
		TextView textView2 = (TextView) findViewById(R.id.textview2);
		TextView textView3 = (TextView) findViewById(R.id.textview3);
		TextView textView4 = (TextView) findViewById(R.id.textview4);
		File textFile = new File(Environment.getExternalStorageDirectory()
				.getPath() + "MyApp/name.txt");
		String[] t = textFile.list();
		for (int i = 0; i < t.length; i++) {
			textList.add(t[i]);
		}
		Collections.shuffle(textList);
		textView1.setText(textList.get(0));
		textView2.setText(textList.get(1));
		textView3.setText(textList.get(2));
		textView4.setText(textList.get(3));
		Log.e("textList", "" + textList.size());

	}*/

}
