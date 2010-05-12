package com.basior.learning;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorRunner implements SensorEventListener, Runnable {

	private static final String MEASURE_TABLE = "measure";
	private static final String RUN_DEFINITION_TABLE = "runDefinition";
	private static final int VERSION = 1;
	public static final String DBNAME = "sensors.db";
	
	private SQLiteDatabase db;
	private SensorManager sm;
	private Sensor sensor;
	private int rate;
	private long runId;
	private long numberOfsamples;
	private boolean shouldRun;
	
	private MySQLiteHelper helper;
	
	private List<Sample> samples;
	
	Thread thread;
	
	public SensorRunner(Context context,SensorManager sm, Sensor sensor, int rate) {
		super();
		this.sensor = sensor;
		this.rate = rate;
		this.sm = sm;
		
		helper = new MySQLiteHelper(context, DBNAME, null, VERSION);
		db = helper.getWritableDatabase();
		insertRunDescription();
		numberOfsamples = 0;
	}


	public void run()
	{
		Sample sample = null;
		
		while(shouldRun())
		{
			synchronized(this) {
				if (samples.size() == 0)
				{
					try {
						wait();
					} catch (InterruptedException e) {
						return;
					}
				}
								
				sample = samples.remove(0);
			}
			
			insertSampleIntoDb(sample);
		}
		
	}


	private void insertSampleIntoDb(Sample sample) {
		ContentValues content = new ContentValues();
		content.put("runId", runId);
		content.put("timestamp", sample.timestamp);
		content.put("value1", sample.values[0]);
		content.put("value2", sample.values[1]);
		content.put("value3", sample.values[2]);
		db.insert(MEASURE_TABLE, null, content);
		numberOfsamples++;
	}


	private void prepareToRun() {
		setShouldRun(true);
		samples = new LinkedList<Sample>();
		db = helper.getWritableDatabase();
		sm.registerListener(this, sensor, rate);
	}
	

	public void start()
	{
		prepareToRun();
		thread = new Thread(this);
		thread.start();
	}

	public void stop()
	{
		sm.unregisterListener(this);
		setShouldRun(false);
		
		if (thread.isAlive())
		{
			thread.interrupt();		
		}
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		db.close();
	}


	
	
	private void insertRunDescription() {
		ContentValues content = new ContentValues();
		content.put("sensor", sensor.getName());
		content.put("rate", rate);
		
		runId = db.insert(RUN_DEFINITION_TABLE, null, content);
	}	

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		Sample s = new Sample();
		s.values[0] = event.values[0];
		s.values[1] = event.values[1];
		s.values[2] = event.values[2];
		s.timestamp = event.timestamp;
		
		synchronized(this)
		{
			samples.add(s);
			notify();
		}
		
	}
	
	public long getNumberOfSamples() {
		return numberOfsamples;
	}


	public boolean shouldRun() {
		return shouldRun;
	}


	public void setShouldRun(boolean shouldRun) {
		this.shouldRun = shouldRun;
	}
	
	
}
