package com.lalitha.musicpuzzle;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class LeveltwoActivity extends Activity implements
SeekBar.OnSeekBarChangeListener 
{
  
	SQLiteDatabase db;
	MyDBHelper helper;
	 Cursor cursor;
	String path2,snamelevel2;
	private SeekBar songProgressBar2;

	MediaPlayer mp1;
	Handler thandler;
	
	Handler lhandler, handler2;
String p,q,r,s;
 

Button option21,option22,option23,option24;
int someRandomNo,one,two,three,four;
String[] answ2;

String[] options2;
String[] optionarr2;
HashMap<String, String> myMap1 = new HashMap<String, String>();
String[] arval2,arval3;
private int n;
String msgl1="teri meri meri teri.mp3",msgl2="dil to paagal hai.MP3",msgl3="backstreet boys.MP3";
String btn1,btn2,btn3,btn4;
static String score1="0";
static int count1=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leveltwo);

		helper = new MyDBHelper(this);
		db = helper.getWritableDatabase();
		option21=(Button)findViewById(R.id.ans21);
		option22=(Button)findViewById(R.id.ans22);
		option23=(Button)findViewById(R.id.ans23);
		option24=(Button)findViewById(R.id.ans24);
		 
		 
		
		 
		     
		 
					 try {
						playsong2();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      	 
					 try {
						if(count1==10)
						 {
							 Toast.makeText(getApplicationContext(), "your score is"+score1, Toast.LENGTH_LONG).show();
							
						 }
						 if(count1==15)
						 {
							 AlertDialog.Builder myDialog = new AlertDialog.Builder(
										this);
								myDialog.setTitle("your Score is"+score1+"\n"+"Do you want to continue?");

								myDialog.setPositiveButton("Exit",
										new DialogInterface.OnClickListener() {
											// do something when the button is clicked
											public void onClick(DialogInterface arg0,
													int arg1) {
												
												mp1.stop();
												Intent intent = new Intent(Intent.ACTION_MAIN);
							                        intent.addCategory(Intent.CATEGORY_HOME);
							                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							                        startActivity(intent);
							                        finish();
											
											}

										});
								myDialog.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int which) { 
							            // do nothing
							        }
							     });
								myDialog.show();
								count1=0;

							}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		

		
	}
	
	public void playsong2()
	{
         
		songProgressBar2 = (SeekBar) findViewById(R.id.songProgressBar2);

		songProgressBar2.setOnSeekBarChangeListener(this);
		
		count1=count1+1;
		System.out.println("countttttttttttttttttttttttttttttttt     "+count1);
		
		
		lhandler = new Handler();
		
				
	
		

		
		cursor = db.rawQuery("select * from audiolevel2 ORDER BY RANDOM() LIMIT 1",
				null);
		if (cursor != null) {
			cursor.moveToFirst();

			do {
				path2 = cursor.getString(cursor.getColumnIndex("audiopath"));
				snamelevel2=cursor.getString(cursor.getColumnIndex("audioname"));
				
				

			} while (cursor.moveToNext());
		}
		
		

		try {
			mp1 = new MediaPlayer();
			mp1.setDataSource(path2);
			mp1.prepare();
			mp1.start();
			songProgressBar2.setProgress(0);
			songProgressBar2.setMax(10000);

			updatePosition();

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		thandler = new Handler();
		thandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (mp1.isPlaying())
					mp1.stop();

			}

		}, 10000);
		 Integer[] arr = new Integer[4];
	        for (int i = 0; i < arr.length; i++) {
	            arr[i] = i;
	        }
	        Collections.shuffle(Arrays.asList(arr));
	        System.out.println("optionsssssssssssssssss"+Arrays.toString(arr));
	        String arrayStr=Arrays.toString(arr);
	      
	        System.out.println("stringggggggggggggggg"+ arrayStr);
	         optionarr2 = arrayStr.split(",");
	         System.out.println("optionarrrrrrrrrrrrr " + optionarr2[0]+optionarr2[1]+optionarr2[2]+optionarr2[3]);
	        
	         arval2=optionarr2[0].split("\\[");
	         System.out.println("helloooooooooooaravalllllllllll"+arval2[1]);
	         arval3=optionarr2[3].split("\\]");
	         System.out.println("hellooooooooooo"+arval3[0]);
	         p=arval2[1].trim();
	         s=arval3[0].trim();
	         q=optionarr2[1].trim();
	         r=optionarr2[2].trim();
	         System.out.println("optionssssssssssssssss"+p+q+r+s);
	         options2=new String[4];
	        
	         options2[0]=p;
	         options2[1]=q;
	         options2[2]=r;
	         options2[3]=s;
	         System.out.println("ssssssssssssssssssss"+options2[0]+options2[1]+options2[2]+options2[3]);
	       
		       
	      
	        	
	       
				
				

	           myMap1.put(options2[0],snamelevel2);
	           
	         String randoml1 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);
	         
	         if(snamelevel2.equals(randoml1))
	         {
	        	 String  r1 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);
	        	 myMap1.put(options2[1],r1);
		         
	         }
	         else
	         {   if(randoml1!=null)
	             {
	        	 myMap1.put(options2[1],randoml1);
	             }
	            else
	            {
	            	 myMap1.put(options2[1],msgl1);
	            }
	         }
	        
	         
	         String randoml2 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);  
	         if(snamelevel2.equals(randoml2))
	         {
	        	 String  r2 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);
	        	 myMap1.put(options2[2],r2);
		         
	         }
	         else
	         {   if(randoml2!=null)
                 {
        	      myMap1.put(options2[2],randoml2);
                  }
	             else
	             {	 
	        	 myMap1.put(options2[2],msgl2);
	             }
	         }
	         
	         
	       
	         
	         String randoml3 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);
	         if(snamelevel2.equals(randoml3))
	         {
	        	 String  r3 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);
	        	 myMap1.put(options2[3],r3);
		         
	         }
	         else
	         {    if(randoml3!=null)
                  {
    	           myMap1.put(options2[3],randoml3);
                   }
	        	 myMap1.put(options2[3],msgl3);
	         }
	        
	         
	         
	        /* for(int j=0;j<options.length;j++)
        	 {
	        	 System.out.println(myMap.get(options[j])+"===");
        	 }*/
	         answ2=new String[4];
	         for(int i=0;i<4;i++)
	         {
	        	  
	        	 
	        	 
	        	 for(int j=0;j<options2.length;j++)
	        	 {	    
	        		 try {
						 n=Integer.parseInt(options2[j]);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		 
	        	if(i==n)
	        	{
	        		 answ2[i]=myMap1.get(options2[j]); 
	        		 System.out.println("optionnnnnnnnn"+answ2[i]);
	        		 
	        	}	 
	        	 } 
	        	 
	         }	
	     
	         System.out.println("songssssssssssss"+answ2[0]+answ2[1]+answ2[2]+answ2[3]);
        	 
        	 
        	 option21.setText(answ2[0]);
        	 option22.setText(answ2[1]);
        	 option23.setText(answ2[2]);
        	 option24.setText(answ2[3]);
        	 
        	option21.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String btn1=option21.getText().toString();
					if(btn1.equals(snamelevel2))
					{
						option21.setBackgroundColor(Color.GREEN);
						score1=(Integer.parseInt(score1)+100)+"";
						System.out.println("scoreeeeeeeeeeeeeeee1   "+score1);
						mp1.stop();
						Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
						startActivity(i);
					}
					else
					{
						option21.setBackgroundColor(Color.RED);
                    btn2=option22.getText().toString();
						
						if(btn2.equals(snamelevel2))
						{
							option22.setBackgroundColor(Color.GREEN);
							
							mp1.stop();
							Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
							startActivity(i);
						}
						 btn3=option23.getText().toString();
						if(btn3.equals(snamelevel2))
						{
							option23.setBackgroundColor(Color.GREEN);
							
							mp1.stop();
							Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
							startActivity(i);
						}
						 btn4=option24.getText().toString();
						if(btn4.equals(snamelevel2))
						{
							option24.setBackgroundColor(Color.GREEN);
							mp1.stop();
							Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
							startActivity(i);
						}
					}
					
				}
			});
        	
option22.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String btn2=option22.getText().toString();
					if(btn2.equals(snamelevel2))
					{
						option22.setBackgroundColor(Color.GREEN);
						score1=(Integer.parseInt(score1)+100)+"";
						System.out.println("scoreeeeeeeeeeeeeeee1   "+score1);
						mp1.stop();
						
						
						Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
						startActivity(i);
					}
					else
					{
						option22.setBackgroundColor(Color.RED);
						
					 btn1=option22.getText().toString();
	                      btn3=option23.getText().toString();
	                     btn4=option24.getText().toString();
							
							if(btn1.equals(snamelevel2))
							{
								option21.setBackgroundColor(Color.GREEN);
								mp1.stop();
								Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
								startActivity(i);
							}
							
							if(btn3.equals(snamelevel2))
							{
								option23.setBackgroundColor(Color.GREEN);
								mp1.stop();
								Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
								startActivity(i);
							}
							
							if(btn4.equals(snamelevel2))
							{
								option24.setBackgroundColor(Color.GREEN);
								mp1.stop();
								Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
								startActivity(i);
							}
						}
						
					}
				});
option23.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String btn3=option23.getText().toString();
		if(btn3.equals(snamelevel2))
		{
			option23.setBackgroundColor(Color.GREEN);
			score1=(Integer.parseInt(score1)+100)+"";
			System.out.println("scoreeeeeeeeeeeeeeee1   "+score1);
			mp1.stop();
			
			Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
			startActivity(i);
		}
		else
		{
			option23.setBackgroundColor(Color.RED);
			String btn2=option22.getText().toString();
			String btn1=option23.getText().toString();
			String btn4=option24.getText().toString();
			if(btn1.equals(snamelevel2))
			{
				option21.setBackgroundColor(Color.GREEN);
				mp1.stop();
				Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
				startActivity(i);
			}
			
			if(btn2.equals(snamelevel2))
			{
				option22.setBackgroundColor(Color.GREEN);
				mp1.stop();
				Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
				startActivity(i);
			}
			
			
			if(btn4.equals(snamelevel2))
			{
				option24.setBackgroundColor(Color.GREEN);
				mp1.stop();
				Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
				startActivity(i);
			}
		}
		
	}
});
option24.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String btn4=option24.getText().toString();
		if(btn4.equals(snamelevel2))
		{
			option24.setBackgroundColor(Color.GREEN);
			score1=(Integer.parseInt(score1)+100)+"";
			System.out.println("scoreeeeeeeeeeeeeeee1   "+score1);
			mp1.stop();
			
			Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
			startActivity(i);
		}
		else
		{
			option24.setBackgroundColor(Color.RED);
			 btn1=option24.getText().toString().trim();
		     btn2=option22.getText().toString().trim();
		     btn3=option23.getText().toString().trim();
			if(btn1.equals(snamelevel2))
			{
				option21.setBackgroundColor(Color.GREEN);
				mp1.stop();
				Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
				startActivity(i);
			}
			
			
			if(btn2.equals(snamelevel2))
			{
				option22.setBackgroundColor(Color.GREEN);
				mp1.stop();
				Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
				startActivity(i);
			}
			
			if(btn3.equals(snamelevel2))
			{
				option23.setBackgroundColor(Color.GREEN);
				mp1.stop();
				Intent i=new Intent(LeveltwoActivity.this,LeveltwoActivity.class);
				startActivity(i);
			}
			
		}
		
	}
}); 	 


		
	}
	

	private final Runnable updatePositionRunnable = new Runnable() {
		public void run() {
			updatePosition();
		}
	};

	private void updatePosition() {
		lhandler.removeCallbacks(updatePositionRunnable);
		if (mp1 != null)

			songProgressBar2.setProgress(mp1.getCurrentPosition());

		lhandler.postDelayed(updatePositionRunnable, 666);

	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		mp1.seekTo(arg1);
	}

	@Override
	public void onStartTrackingTouch(SeekBar songSeekBar) {
		// TODO Auto-generated method stub
		lhandler.removeCallbacks(updatePositionRunnable);
	

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		lhandler.removeCallbacks(updatePositionRunnable);
		int totalDuration = mp1.getDuration();
		int currentPosition = mp1.getCurrentPosition();
		mp1.seekTo(currentPosition);
		lhandler.postDelayed(updatePositionRunnable, 10000);

	}
	
	

	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
			mp1.stop();
			Intent i=new Intent(LeveltwoActivity.this,MainActivity.class);
			startActivity(i);

			// moveTaskToBack(false);

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

}
