package com.lalitha.musicpuzzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;

public class LevelsActivity extends Activity {
	SQLiteDatabase db;
	MyDBHelper helper;
	Cursor c;
	String  path;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_levels);
		helper=new MyDBHelper(this);
		db=helper.getWritableDatabase();
		 mp=MediaPlayer.create(LevelsActivity.this, R.raw.music);
		mp.start();
	}
	
	public void level1click(View v)
	{
		mp.stop();
		Intent i=new Intent(LevelsActivity.this,LeveloneActivity.class);
		startActivity(i);
	}
	
	public void level2click(View v)
	{
		
		mp.stop();
		Intent i=new Intent(LevelsActivity.this,LeveltwoActivity.class);
		startActivity(i);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.levels, menu);
		return true;
	}
	
	
	

	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
		
			AlertDialog alertbox = new AlertDialog.Builder(this)
			.setMessage("Do you want to exit application?")
			.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						// do something when the button is clicked
						public void onClick(DialogInterface arg0, int arg1) {

							mp.stop();
							Intent intent = new Intent(Intent.ACTION_MAIN);
		                        intent.addCategory(Intent.CATEGORY_HOME);
		                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		                        startActivity(intent);
		                        finish();
							

						}
					})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {

				// do something when the button is clicked
				public void onClick(DialogInterface arg0, int arg1) {
				}
			}).show();


			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


}
