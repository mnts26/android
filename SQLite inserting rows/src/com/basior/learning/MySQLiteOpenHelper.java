package com.basior.learning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	
	
	private static final String CREATE_TABLE_QUERY = "create table if not exists times ("
			+ "id integer primary key autoincrement, " +
					"my_date date, " +
					"creation_date date default CURRENT_DATE )";
	private static final int version = 1;
	private static final String name = "times"; // in memory

	public MySQLiteOpenHelper(Context context) {
		super(context, name, null, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		onCreate(db);
	}

}
