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

public class LeveloneActivity extends Activity implements
		SeekBar.OnSeekBarChangeListener {
	SQLiteDatabase db;
	MyDBHelper helper;
	Cursor c;
	String path,songname,snamelevel1;
	private SeekBar songProgressBar;

	MediaPlayer mp;
	Handler mhandler;
	
	Handler handler, shandler;
String p,q,r,s;
static String score="0";
 

Button option1,option2,option3,option4;
 
String[] answ;

String[] options;
String[] optionarr;
HashMap<String, String> myMap = new HashMap<String, String>();
String[] arval,arval1;
private int n;
String msg1="Every thing at once",msg2="bholi si surat",msg3="Life is beautiful";
static int count=0;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_levelone);

		helper = new MyDBHelper(this);
		db = helper.getWritableDatabase();
		option1=(Button)findViewById(R.id.ans1);
		option2=(Button)findViewById(R.id.ans2);
		option3=(Button)findViewById(R.id.ans3);
		option4=(Button)findViewById(R.id.ans4);
		 
		 
		
		 
		     
		 
					 try {
						playsong();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 try {
						if(count==5)
						 {
							 Toast.makeText(getApplicationContext(), "your score is"+score, Toast.LENGTH_LONG).show();
							
						 }
						 if(count==10)
						 {
							 AlertDialog.Builder myDialog = new AlertDialog.Builder(
										this);
								myDialog.setTitle("your Score is"+score+"\n"+"Do you want to continue?");

								myDialog.setPositiveButton("Exit",
										new DialogInterface.OnClickListener() {
											// do something when the button is clicked
											public void onClick(DialogInterface arg0,
													int arg1) {
												
												mp.stop();
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
								count=0;

							}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
					
		  
		      	 
			
		
		

		/*songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);

		songProgressBar.setOnSeekBarChangeListener(this);
		*/
	}
	
	public void playsong()
	{
         
		songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);

		songProgressBar.setOnSeekBarChangeListener(this);
		
		count=count+1;
		System.out.println("countttttttttttttttttttttttttttttttt     "+count);
		handler = new Handler();
		
				
	
		

		
		c = db.rawQuery("select * from audiolevel1 ORDER BY RANDOM() LIMIT 1",
				null);
		if (c != null) {
			c.moveToFirst();

			do {
				path = c.getString(c.getColumnIndex("audiopath"));
				snamelevel1=c.getString(c.getColumnIndex("audioname"));
				
				

			} while (c.moveToNext());
		}
		
		

		try {
			mp = new MediaPlayer();
			mp.setDataSource(path);
			mp.prepare();
			mp.start();
			songProgressBar.setProgress(0);
			songProgressBar.setMax(15000);

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

		mhandler = new Handler();
		mhandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (mp.isPlaying())
					mp.stop();

			}

		}, 15000);
		 Integer[] arr = new Integer[4];
	        for (int i = 0; i < arr.length; i++) {
	            arr[i] = i;
	        }
	        Collections.shuffle(Arrays.asList(arr));
	        System.out.println("optionsssssssssssssssss"+Arrays.toString(arr));
	        String arrayStr=Arrays.toString(arr);
	      
	        System.out.println("stringggggggggggggggg"+ arrayStr);
	         optionarr = arrayStr.split(",");
	         System.out.println("optionarrrrrrrrrrrrr " + optionarr[0]+optionarr[1]+optionarr[2]+optionarr[3]);
	        
	         arval=optionarr[0].split("\\[");
	         System.out.println("helloooooooooooaravalllllllllll"+arval[1]);
	         arval1=optionarr[3].split("\\]");
	         System.out.println("hellooooooooooo"+arval1[0]);
	         p=arval[1].trim();
	         s=arval1[0].trim();
	         q=optionarr[1].trim();
	         r=optionarr[2].trim();
	         System.out.println("optionssssssssssssssss"+p+q+r+s);
	         options=new String[4];
	        
	         options[0]=p;
	         options[1]=q;
	         options[2]=r;
	         options[3]=s;
	         System.out.println("ssssssssssssssssssss"+options[0]+options[1]+options[2]+options[3]);
	       
		       
	      
	        	
	       
				
				

	           myMap.put(options[0],snamelevel1);
	           
	         String random1 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);
	         
	         if(snamelevel1.equals(random1))
	         {
	        	 String  r1 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);
	        	 myMap.put(options[1],r1);
		         
	         }
	         else
	         {   if(random1!=null)
	            {
	        	 myMap.put(options[1],random1);
	            }
	         else
	         {
	        	 myMap.put(options[1],msg1);
	         }
	         }
	        
	         
	         String random2 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);  
	         if(snamelevel1.equals(random2))
	         {
	        	 String  r2 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);
	        	 myMap.put(options[2],r2);
		         
	         }
	         else
	         {    if(random2!=null)
	            {
	        	 myMap.put(options[2],random2);
	            }
	         else
	         {
	        	 myMap.put(options[2],msg2);
	         }	 
	         }
	         
	         
	       
	         
	         String random3 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);
	         if(snamelevel1.equals(random3))
	         {
	        	 String  r3 = (MainActivity.songlist[new Random().nextInt(MainActivity.songlist.length)]);
	        	 myMap.put(options[3],r3);
		         
	         }
	         else
	         {     if(random3!=null)
	            {
	        	 myMap.put(options[3],random3);
	            }
	         else
	         {
	        	 myMap.put(options[3],msg3);
	         }
	         }
	        
	         
	         
	        /* for(int j=0;j<options.length;j++)
        	 {
	        	 System.out.println(myMap.get(options[j])+"===");
        	 }*/
	         answ=new String[4];
	         for(int i=0;i<4;i++)
	         {
	        	  
	        	 
	        	 
	        	 for(int j=0;j<options.length;j++)
	        	 {	    
	        		 try {
						 n=Integer.parseInt(options[j]);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		 
	        	if(i==n)
	        	{
	        		 answ[i]=myMap.get(options[j]); 
	        		 System.out.println("optionnnnnnnnn"+answ[i]);
	        		 
	        	}	 
	        	 } 
	        	 
	         }	
	     
	         System.out.println("songssssssssssss"+answ[0]+answ[1]+answ[2]+answ[3]);
        	 
        	 
        	 option1.setText(answ[0]);
        	 option2.setText(answ[1]);
        	 option3.setText(answ[2]);
        	 option4.setText(answ[3]);
        	 
        	option1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String btn1=option1.getText().toString();
					if(btn1.equals(snamelevel1))
					{
						option1.setBackgroundColor(Color.GREEN);
						score=(Integer.parseInt(score)+100)+"";
						System.out.println("scoreeeeeeeeeeeeeeee   "+score);
						mp.stop();
						
						Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
						startActivity(i);
					}
					else
					{
						option1.setBackgroundColor(Color.RED);
						
						String button2=option2.getText().toString();
						
						if(button2.equals(snamelevel1))
						{
							option2.setBackgroundColor(Color.GREEN);
							
							mp.stop();
							Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
							startActivity(i);
						}
						String button3=option3.getText().toString();
						if(button3.equals(snamelevel1))
						{
							option3.setBackgroundColor(Color.GREEN);
							
							mp.stop();
							Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
							startActivity(i);
						}
						String button4=option4.getText().toString();
						if(button4.equals(snamelevel1))
						{
							option4.setBackgroundColor(Color.GREEN);
							mp.stop();
							Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
							startActivity(i);
						}
					}
					
				}
			});
        	
option2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String btn2=option2.getText().toString();
					if(btn2.equals(snamelevel1))
					{
						option2.setBackgroundColor(Color.GREEN);
						score=(Integer.parseInt(score)+100)+"";
						System.out.println("scoreeeeeeeeeeeeeeee   "+score);
						mp.stop();
						
						
						Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
						startActivity(i);
					}
					else
					{
						option2.setBackgroundColor(Color.RED);
						
                      String button1=option2.getText().toString();
                      String button3=option3.getText().toString();
                      String button4=option4.getText().toString();
						
						if(button1.equals(snamelevel1))
						{
							option1.setBackgroundColor(Color.GREEN);
							mp.stop();
							Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
							startActivity(i);
						}
						
						if(button3.equals(snamelevel1))
						{
							option3.setBackgroundColor(Color.GREEN);
							mp.stop();
							Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
							startActivity(i);
						}
						
						if(button4.equals(snamelevel1))
						{
							option4.setBackgroundColor(Color.GREEN);
							mp.stop();
							Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
							startActivity(i);
						}
					}
					
				}
			});
option3.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String btn3=option3.getText().toString();
		if(btn3.equals(snamelevel1))
		{
			option3.setBackgroundColor(Color.GREEN);
			score=(Integer.parseInt(score)+100)+"";
			System.out.println("scoreeeeeeeeeeeeeeee   "+score);
			mp.stop();
			
			Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
			startActivity(i);
		}
		else
		{
			option3.setBackgroundColor(Color.RED);
			String button2=option2.getText().toString();
			String button1=option3.getText().toString();
			String button4=option4.getText().toString();
			if(button1.equals(snamelevel1))
			{
				option1.setBackgroundColor(Color.GREEN);
				mp.stop();
				Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
				startActivity(i);
			}
			
			if(button2.equals(snamelevel1))
			{
				option2.setBackgroundColor(Color.GREEN);
				mp.stop();
				Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
				startActivity(i);
			}
			
			
			if(button4.equals(snamelevel1))
			{
				option4.setBackgroundColor(Color.GREEN);
				mp.stop();
				Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
				startActivity(i);
			}
		}
		
	}
});
option4.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String btn4=option4.getText().toString();
		System.out.println("scoreeeeeeeeeeeeeeee   "+score);
		if(btn4.equals(snamelevel1))
		{
			option4.setBackgroundColor(Color.GREEN);
			score=(Integer.parseInt(score)+100)+"";
			mp.stop();
			
			Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
			startActivity(i);
		}
		else
		{
			option4.setBackgroundColor(Color.RED);
			
			String button1=option4.getText().toString().trim();
			String button2=option2.getText().toString().trim();
			String button3=option3.getText().toString().trim();
			if(button1.equals(snamelevel1))
			{
				option1.setBackgroundColor(Color.GREEN);
				mp.stop();
				Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
				startActivity(i);
			}
			
			
			if(button2.equals(snamelevel1))
			{
				option2.setBackgroundColor(Color.GREEN);
				mp.stop();
				Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
				startActivity(i);
			}
			
			if(button3.equals(snamelevel1))
			{
				option3.setBackgroundColor(Color.GREEN);
				mp.stop();
				Intent i=new Intent(LeveloneActivity.this,LeveloneActivity.class);
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
		handler.removeCallbacks(updatePositionRunnable);
		if (mp != null)

			songProgressBar.setProgress(mp.getCurrentPosition());

		handler.postDelayed(updatePositionRunnable, 1000);

	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		mp.seekTo(arg1);
	}

	@Override
	public void onStartTrackingTouch(SeekBar songSeekBar) {
		// TODO Auto-generated method stub
		handler.removeCallbacks(updatePositionRunnable);
	

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		handler.removeCallbacks(updatePositionRunnable);
		int totalDuration = mp.getDuration();
		int currentPosition = mp.getCurrentPosition();
		mp.seekTo(currentPosition);
		handler.postDelayed(updatePositionRunnable, 15000);

	}
	
	


	/*protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		playsong();
	}*/

	
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		/*Intent i=new Intent(LeveloneActivity.this,MainActivity.class);
		startActivity(i);*/
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
			mp.stop();
			Intent i=new Intent(LeveloneActivity.this,MainActivity.class);
			startActivity(i);

			// moveTaskToBack(false);

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

}
