package com.lalitha.musicpuzzle;




import java.io.InputStream;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainScreen extends Activity{

	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.mainscreen);
        
	}
	
	
	
        @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	


		Thread runnerlog=new Thread()
        {
        	public void run()
        	{
        		try
        		{
        			int logoTimer=0;
        			while(logoTimer<2000)
        			{
        				sleep(100);  
        				logoTimer=logoTimer+100;
        			}
        			startActivity(new Intent("com.lalitha.musicpuzzle.MAINACTIVITY"));
        		}catch (Exception e) {
					// TODO: handle exception
        			e.printStackTrace();
				}
        	}
        	
        };
        
        runnerlog.start();
    }
}
	


