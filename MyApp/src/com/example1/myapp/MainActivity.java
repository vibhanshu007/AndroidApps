package com.example1.myapp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends Activity {
	private static final JSONObject JSONObject = null;
	ArrayList<String> imageList, textList;
	// private int currentDrawable;
	BitmapImageLoder bitImage;
	private TextView question1, question2, question3, question4, textAnswer1,
			textAnswer2, textAnswer3, textAnswer4;
	private ArrayList<Point> answerPlaceHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bitImage = new BitmapImageLoder();
		imageList = new ArrayList<String>();
		textList = new ArrayList<String>();
		getImageName();
		getValueFromJSON();
		TextView textAnswer1 = (TextView) findViewById(R.id.question_textview1);
		TextView textAnswer2 = (TextView) findViewById(R.id.question_textview2);
		TextView textAnswer3 = (TextView) findViewById(R.id.question_textview3);
		TextView textAnswer4 = (TextView) findViewById(R.id.question_textview4);
		textAnswer1.setOnTouchListener(new OptionDragAndDrop(this));
		textAnswer2.setOnTouchListener(new OptionDragAndDrop(this));
		textAnswer3.setOnTouchListener(new OptionDragAndDrop(this));
		textAnswer4.setOnTouchListener(new OptionDragAndDrop(this));
	}

	/*
	 * @Override public boolean onTouchEvent(MotionEvent event) { if
	 * (event.getAction() == MotionEvent.ACTION_DOWN) { String text =
	 * "clicked at X" + event.getX() + "clicked at Y" + event.getX();
	 * Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	 * 
	 * System.out.println((int) event.getX() + "," + (int) event.getY());
	 * Log.v("IN DP --->", (int) event.getX() / 1.5 + "" + "," + (int)
	 * event.getY() / 1.5); } return super.onTouchEvent(event);
	 * 
	 * }
	 */

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
		Animation anim4 = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.abc_popup_enter);
		question1 = (TextView) findViewById(R.id.question_textview1);
		question2 = (TextView) findViewById(R.id.question_textview2);
		question3 = (TextView) findViewById(R.id.question_textview3);
		question4 = (TextView) findViewById(R.id.question_textview4);
		question1.startAnimation(anim4);
		question2.startAnimation(anim4);
		question3.startAnimation(anim4);
		question4.startAnimation(anim4);
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

	// ///How to parse JSON Value From the sdCard Importent...

	public void getValueFromJSON() {
		ArrayList<String> imageName = new ArrayList<String>();
		try {
			TextView textView1 = (TextView) findViewById(R.id.question_textview1);
			TextView textView2 = (TextView) findViewById(R.id.question_textview2);
			TextView textView3 = (TextView) findViewById(R.id.question_textview3);
			TextView textView4 = (TextView) findViewById(R.id.question_textview4);
			File myFile = new File(Environment.getExternalStorageDirectory(),
					"/MyApp/images.json");

			FileInputStream stream = new FileInputStream(myFile);
			String jsonStr = null;

			try {
				FileChannel fc = stream.getChannel();
				MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
						fc.size());
				Log.e("Formating", "JSONFromating");
				jsonStr = Charset.defaultCharset().decode(bb).toString();
			} finally {

				stream.close();
			}

			JSONObject jsonObj = new JSONObject(jsonStr);

			// Getting data JSON Array nodes
			JSONArray data = jsonObj.getJSONArray("Question");

			// looping through All nodes
			for (int i = 0; i < data.length(); i++) {
				JSONObject c = data.getJSONObject(i);

				String id = c.getString("name");
				imageName.add(id);
				/*
				 * String title = c.getString("title"); String duration =
				 * c.getString("duration");
				 */
				// use > int id = c.getInt("duration"); if you want get an int
				// String id1 = c.getString("image");

				// tmp hashmap for single node
				// HashMap<String, String> parsedData = new HashMap<String,
				// String>();

				// adding each child node to HashMap key => value
				/*
				 * parsedData.put("id", id); parsedData.put("title", title);
				 * parsedData.put("duration", duration); // do what do you want
				 * on your interface
				 */
			}
			textList.addAll(imageName);
			Log.d("textList", "" + textList);
			Collections.shuffle(textList);
			textView1.setText(textList.get(0));
			textView2.setText(textList.get(1));
			textView3.setText(textList.get(2));
			textView4.setText(textList.get(3));
			Log.e("textList", "" + textList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getTextCoordinate() {
		
	}

	public ArrayList<Point> getAnswerPlaceHolder() {
		return getAnswerPlaceHolder();
	}

	public void setAnswerPlaceHolder(ArrayList<Point> answerPlaceHolder) {
		this.answerPlaceHolder = answerPlaceHolder;
	}

}
