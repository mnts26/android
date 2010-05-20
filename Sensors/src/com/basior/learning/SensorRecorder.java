package com.basior.learning;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.basior.learning.data.Sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorRecorder implements SensorEventListener, Runnable {

	private static final int VERSION = 2;
	public static final String DBNAME = "sensors.db";
	
	private SQLiteDatabase db;
	private SensorManager sm;
	private Sensor sensor;
	private int rate;
	private long runId;
	private long numberOfsamples;
	private boolean isRunning;
	
	private MySQLiteHelper helper;
	
	private List<Sample> samples;
	
	Thread thread;
	
	public SensorRecorder(Context context,SensorManager sm, Sensor sensor, int rate) {
		super();
		this.sensor = sensor;
		this.rate = rate;
		this.sm = sm;
		
		helper = new MySQLiteHelper(context, DBNAME, null, VERSION);
	}


	public void run()
	{
		Sample sample = null;
		
		while(isRunning())
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




	private void prepareToRun() {
		setRunnning(true);
		samples = new LinkedList<Sample>();
		numberOfsamples = 0;
		db = helper.getWritableDatabase();
		insertRunDescription();		
		sm.registerListener(this, sensor, rate);
	}
	

	public void start()
	{
		Log.d("Sensor runner", "Start");
		prepareToRun();
		thread = new Thread(this);
		thread.start();
	}

	public void stop()
	{
		sm.unregisterListener(this);
		setRunnning(false);
		
		if (thread.isAlive())
		{
			thread.interrupt();		
		}
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		updateStopDate();
		db.close();
		Log.d("Sensor runner", "Stop");
	}


	
	
	private void updateStopDate() {
		ContentValues content = new ContentValues();
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		content.put("end_date", formatter.format(new Date()));
	
		db.update(MySQLiteHelper.RUN_TABLE, content, "runId=?", new String[] { "" + runId});
	}


	private void insertSampleIntoDb(Sample sample) {
		ContentValues content = new ContentValues();
		content.put("runId", runId);
		content.put("timestamp", sample.timestamp);
		content.put("value1", sample.values[0]);
		content.put("value2", sample.values[1]);
		content.put("value3", sample.values[2]);
		db.insert(MySQLiteHelper.MEASURE_TABLE, null, content);
		numberOfsamples++;
	}

	
	private void insertRunDescription() {
		ContentValues content = new ContentValues();
		content.put("sensor", sensor.getName());
		content.put("rate", rate);
		
		runId = db.insert(MySQLiteHelper.RUN_TABLE, null, content);
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


	public boolean isRunning() {
		return isRunning;
	}


	public void setRunnning(boolean shouldRun) {
		this.isRunning = shouldRun;
	}
	
	
}
