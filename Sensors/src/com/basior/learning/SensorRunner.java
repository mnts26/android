package com.basior.learning;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorRunner implements SensorEventListener {

	private static final int VERSION = 0;
	private static final String NAME = null;
	
	private SQLiteDatabase db;
	private Context context;
	private SensorManager sm;
	private Sensor sensor;
	private int rate;
	private int runId;
	
	
	private SensorRunner(Context context,SensorManager sm, Sensor sensor, int rate) {
		super();
		this.context = context;
		this.sensor = sensor;
		this.rate = rate;
		this.sm = sm;
		
		db = new MySQLiteHelper(context, NAME, null, VERSION).getWritableDatabase();
		insertRunDescription();		
	}


	public void start()
	{
		sm.registerListener(this, sensor, rate);
		// TODO: update startdt
	}
	

	public void stop()
	{
		sm.unregisterListener(this);
		insertEndDate();
		db.close();
	}


	private void insertEndDate() {
		ContentValues content = new ContentValues();
		content.put("endtDt", new Date().getTime());
		// TOOD: where clause
		db.update("main.runDefinition", content, "", null);
		

	}

	
	private void insertRunDescription() {
		ContentValues content = new ContentValues();
		content.put("sensor", sensor.getName());
		content.put("rate", rate);
		
		db.insert("main.runDefinition", "nullColumnHack", content);
		
		// TODO: get run id
	}	

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		ContentValues content = new ContentValues();
		content.put("runId", runId);
		content.put("timestamp", event.timestamp);
		content.put("value1", event.values[0]);
		content.put("value2", event.values[1]);
		content.put("value3", event.values[2]);
		db.insert("main.measure", "nullColumnHack", content);
	}
}
