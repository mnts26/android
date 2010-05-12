package com.basior.learning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MySQLiteHelper extends SQLiteOpenHelper {

	private static final String CREATE_MEASURE_QUERY = "CREATE TABLE measure ( " +
		    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		    "runId INTEGER," +
		    "timestamp INTEGER," +
		    "value1 REAL DEFAULT (0)," +
		    "value2 REAL DEFAULT (0)," +
		    "value3 REAL DEFAULT (0)" +
		    ")";

	private static final String CREATE_RUN_DEFINITION_QUERY = "CREATE TABLE runDefinition (" +
		    "runId INTEGER PRIMARY KEY AUTOINCREMENT," +
		    "sensor TEXT," +
		    "rate INTEGER"+
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
		onCreate(db);
	}

}
