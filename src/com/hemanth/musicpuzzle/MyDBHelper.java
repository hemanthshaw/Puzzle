package com.lalitha.musicpuzzle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

	Context ctx;
	String create_sql1, create_sql2, create_sql3;

	private static final String DATABASE_NAME = "lalitha";
	private static final int DATABASE_VERSION = 1;

	SQLiteDatabase sdb;

	public MyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		sdb = getWritableDatabase();
		sdb = getReadableDatabase();
		ctx = context;
	}

	public void onCreate(SQLiteDatabase db) {
		create_sql1 = "CREATE TABLE IF NOT EXISTS audiolevel1(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "audiopath VARCHAR NOT NULL UNIQUE," + "audioname VARCHAR NOT NULL UNIQUE);";
		db.execSQL(create_sql1);
		
		create_sql2 = "CREATE TABLE IF NOT EXISTS audiolevel2(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "audiopath VARCHAR NOT NULL UNIQUE," + "audioname VARCHAR NOT NULL UNIQUE);";
		db.execSQL(create_sql2);
		
		/*create_sql3 = "CREATE TABLE IF NOT EXISTS audiolevel3(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "audiopath VARCHAR NOT NULL UNIQUE," + "audioname VARCHAR NOT NULL UNIQUE);";
		db.execSQL(create_sql3);*/
		
		
		
	}

	

	
	
	
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int paramInt1, int paramInt2) {
		switch (paramInt1) {
		case 1:
			db.execSQL(create_sql1);
			// we want both updates, so no break statement here...
		case 2:
			db.execSQL(create_sql2);
			
		case 3:
			db.execSQL(create_sql3);		
		}
		
	}

}
