package com.basior.learning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.provider.OpenableColumns;

public class MySQLiteHelper extends SQLiteOpenHelper {

	static final String MEASURE_TABLE = "measurement";
	static final String RUN_TABLE = "run";
	
	private static final String CREATE_MEASURE_QUERY = "CREATE TABLE " + MEASURE_TABLE + " ( " +
		    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		    "runId INTEGER," +
		    "timestamp INTEGER," +
		    "value1 REAL DEFAULT (0)," +
		    "value2 REAL DEFAULT (0)," +
		    "value3 REAL DEFAULT (0)" +
		    ")";

	private static final String CREATE_RUN_DEFINITION_QUERY = "CREATE TABLE " + RUN_TABLE + " (" +
		    "runId INTEGER PRIMARY KEY AUTOINCREMENT," +
		    "sensor TEXT," +
		    "rate INTEGER,"+
		    "start_date DATETIME DEFAULT CURRENT_TIMESTAMP," +
		    "end_date DATETIME" + 
		")";

	
	public MySQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_MEASURE_QUERY);
		db.execSQL(CREATE_RUN_DEFINITION_QUERY);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("drop table if exists " + RUN_TABLE);
		db.execSQL("drop table if exists " + MEASURE_TABLE);
		onCreate(db);
	}

	
	static public void copyDataBase(Context context, String dbName, String toPath) throws Throwable
 {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(
					"/data/data/com.basior.learning/databases/" + dbName);
			fos = new FileOutputStream(toPath);

			int n;
			byte[] buffer = new byte[1024];
			while ((n = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, n);
			}
		} finally {
			if (fis != null)
				try {
					fis.close();
				} finally {
					if (fos != null) {
						fos.flush();
						fos.close();
					}
				}
		}
	}

}
