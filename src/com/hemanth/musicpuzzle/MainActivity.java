package com.lalitha.musicpuzzle;



import java.io.InputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {
    private int totalsongscount;

    private String[] arrPath,albumarr;
    private String[] nameFile;
    String mp3song;
    String mp3Pattern = ".mp3";
    String MP3Pattern = ".MP3";
    SQLiteDatabase db;
    MyDBHelper helper;
    Cursor c;
    SimpleCursorAdapter cadapter;
public static  int level1count ,mp3songscount;
  int level2count;
    int level3count;

  public static String[] songlist,level2songs,albumlist;
  public static String[] songpath;

   
    ListView lv;
    int j=0;
  public static  int k,count;
  InputStream stream = null;
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           /* GifMovieView gifView = new GifMovieView(this);
            gifView.setGIFResource(R.drawable.piggy);
            setContentView(gifView);*/
            
         
           setContentView(R.layout.activity_main);
           
            helper=new MyDBHelper(this);
            db=helper.getWritableDatabase();
     
           
            
           // final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Audio.Media._ID };
            new LoadingSongs().execute();
           
               }
    
    private class LoadingSongs extends AsyncTask<Void, Void, Void> {
    	

		/** The dialog. */
		private final ProgressDialog dialog = new ProgressDialog(
				MainActivity.this);

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		protected void onPreExecute() {
			this.dialog.setMessage("Please wait...");
		
			
			this.dialog.show();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		protected void onPostExecute(final Void unused) {
			
			runOnUiThread(new Runnable(){
				public void run(){
			try {
				
	/*c = db.rawQuery(
							"select * from audiolevel3", null);
				startManagingCursor(c);
				 cadapter = new SimpleCursorAdapter(MainActivity.this, R.layout.mylist, c,
		    				new String[] { "audioname" }, new int[] { R.id.text1});

		    		setListAdapter(cadapter);*/
				
				 // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
			
				Intent i=new Intent(MainActivity.this,LevelsActivity.class);
				startActivity(i);
				
			/* ArrayAdapter<String> adapter = new ArrayAdapter<String>  
		         (MainActivity.this,android.R.layout.simple_list_item_1,songlist); 
				 lv.setAdapter(adapter);*/
					dialog.dismiss();
				}
			
			 catch (Exception e) {
				e.printStackTrace();
			}
				}
			});
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			
			try {
				final String orderBy = MediaStore.Images.Media._ID;
				Cursor audiocursor = managedQuery(
				                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
				                null, orderBy);
        
         
				totalsongscount = audiocursor.getCount();
        
         
        arrPath = new String[totalsongscount];
         nameFile = new String[totalsongscount];
         
        songlist=new String[totalsongscount];
        songpath=new String[totalsongscount];
      
         
				for (int i = 0; i <totalsongscount; i++) {
					
					
					
					 audiocursor.moveToPosition(i);
				      
				    
				        
				       
					 int dataColumnIndex = audiocursor.getColumnIndex(MediaStore.Audio.Media.DATA);  
					 
					/* int dataColumnIndex1=audiocursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
				    */
				        
				      arrPath[i]= audiocursor.getString(dataColumnIndex);
				     /* albumarr[i]=audiocursor.getString(dataColumnIndex1);*/
				       
				      
				        
				        
				        nameFile[i] =arrPath[i].substring(arrPath[i].lastIndexOf("/")+1, arrPath[i].length());
				      
				        
				        
				        if (nameFile[i].endsWith(mp3Pattern)||nameFile[i].endsWith(MP3Pattern)){
				   		
				        	 
				        	
				        	 k=j;
				        	 songlist[k]=nameFile[i];
				        	 songpath[k]=arrPath[i];
				        	 /*albumlist[k]=albumarr[i];*/
				        	j++;
				        	count=j;
				        	
				        	
				        
				        } 
       
				        	
				     
				                
				       
				        
				          
				}
				
				
         
				
				/*level1count=count/3;*/
				level1count=count/2;
				//level2count=count-level1count; 
      for (int l = 0; l <level1count; l++) {
					
				 ContentValues cv=new ContentValues();
				  cv.put("audiopath", songpath[l]);	    
				  cv.put("audioname",songlist[l]);
				 /* cv.put("albumname", albumlist[l]);*/
				
				  db.insert("audiolevel1", null, cv);
				    
				  System.out.println("level111111111111111111111111111"+songlist[l]); 
				    	 
				   
				    } 
      
      
      for (int m =level1count; m <count; m++) {
    	  
          
			 ContentValues cv1=new ContentValues();
			  cv1.put("audiopath", songpath[m]);	    
			  cv1.put("audioname",songlist[m]);
			
			  
			  db.insert("audiolevel2", null, cv1);
			  
			 
			  System.out.println("level22222222222222222222"+songlist[m]); 
			  
			 
			   

}
     /* for (int m =level1count; m <level2count; m++) {
    	  
    	          
				 ContentValues cv=new ContentValues();
				  cv.put("audiopath", songpath[m]);	    
				  cv.put("audioname",songlist[m]);
				  cv.put("albumname", albumlist[m]);
				  
				  db.insert("audiolevel2", null, cv);
				  
				 
				  System.out.println("level22222222222222222222"+songlist[m]); 
				  
				 
				   

      }
      for (int n =level2count; n <count; n++) {
				 ContentValues cv=new ContentValues();
				  cv.put("audiopath", songpath[n]);	    
				  cv.put("audioname",songlist[n]);
				  cv.put("albumname", albumlist[n]);
				 
				  
				  db.insert("audiolevel3", null, cv);
				  System.out.println("level3333333333333333333"+songlist[n]); 
				  
      
      }*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;

		}


    
}  
}