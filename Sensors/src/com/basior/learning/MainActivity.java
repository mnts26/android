package com.basior.learning;

import java.util.ArrayList;

import com.basior.learning.data.MonitorState;
import com.basior.learning.data.RecorderState;
import com.basior.learning.data.SensorRate;
import com.basior.learning.data.SensorWrapper;
import com.basior.learning.data.WakeLockType;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity implements SensorEventListener {
    private static final int MENU_SAVE = Menu.FIRST;
	private static final int MENU_QUIT = MENU_SAVE + 1;
	
	Spinner spinner;
	Spinner rateSpinner;
	Spinner wakeLockSpinner;
	ArrayList<SensorWrapper> sensors = new ArrayList<SensorWrapper>();
	Sensor currentSensor;
	
	TextView textViewName;
	TextView textViewResolution;
	TextView textViewMaxRange;
	TextView textViewPower;
	TextView textViewValue1;
	TextView textViewValue2;
	TextView textViewValue3;
	
	Button button;
	
	SensorManager sm;
	PowerManager pm;
	PowerManager.WakeLock wakeLock;
	
	static SensorRecorder recorder = null;
	
	Context context;
	
	int currentRate = 0;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        context = this;
        
		textViewName = (TextView) findViewById(R.id.TextViewName);
		textViewResolution = (TextView) findViewById(R.id.TextViewResolution);
		textViewMaxRange = (TextView) findViewById(R.id.TextViewMaxRange);
		textViewPower = (TextView) findViewById(R.id.TextViewPower);
		textViewValue1 = (TextView) findViewById(R.id.TextViewValue1);
		textViewValue2 = (TextView) findViewById(R.id.TextViewValue2);
		textViewValue3 = (TextView) findViewById(R.id.TextViewValue3);		
	
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		pm = (PowerManager) getSystemService(POWER_SERVICE);
		
		setupRateSpinner();
		setupSensorSpinner();
		setupWakeLockSpinner();
		button = (Button)findViewById(R.id.ButtonStartStop);
		
		if (recorder != null) {
			changeState(RecorderState.RUNNING);
		} else
		{
			changeState(RecorderState.STOPPED);
		}
    }


	private void setupWakeLockSpinner() {
		ArrayAdapter<WakeLockType> adapter = new ArrayAdapter<WakeLockType>(this, android.R.layout.simple_spinner_item, WakeLockType.values());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		wakeLockSpinner = (Spinner)findViewById(R.id.SpinnerWakeLock);
		wakeLockSpinner.setAdapter(adapter);
	}


	private void setupRateSpinner() {
		ArrayAdapter<SensorRate> adapter = new ArrayAdapter<SensorRate>(this, android.R.layout.simple_spinner_item, SensorRate.values());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		rateSpinner = (Spinner)findViewById(R.id.Spinner02);
		rateSpinner.setAdapter(adapter);
		rateSpinner.setOnItemSelectedListener(rateSpinnerListener);
	}


	private void setupSensorSpinner() {
		for (Sensor s: sm.getSensorList(Sensor.TYPE_ALL))
		{
			sensors.add(new SensorWrapper(s));			
		}
		
		ArrayAdapter<SensorWrapper> adapter = new ArrayAdapter<SensorWrapper>(this, android.R.layout.simple_spinner_item, sensors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               
		spinner = (Spinner) findViewById(R.id.Spinner01);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(sensorSpinnerListener);
	}

	
	private final OnItemSelectedListener rateSpinnerListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			currentRate = SensorRate.values()[position].getValue();
			changeState(MonitorState.CHANGE_RATE);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};
	
	private final OnItemSelectedListener sensorSpinnerListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			currentSensor = sensors.get(position).getSensor();
			changeState(MonitorState.CHANGE_SENSOR);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};
	
	private final View.OnClickListener startListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			changeState(RecorderState.START);
		}
	};
	
	private final View.OnClickListener stopListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			changeState(RecorderState.STOP);
		}
	};

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}


	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		textViewValue1.setText("" + sensorEvent.values[0]);
		textViewValue2.setText("" + sensorEvent.values[1]);
		textViewValue3.setText("" + sensorEvent.values[2]);
	}


	@Override
	protected void onPause() {
		super.onPause();
		
		changeState(MonitorState.OFF);
		if (isFinishing())
		{
			changeState(RecorderState.STOP);
		}
		
	}


	@Override
	protected void onResume() {
		super.onResume();
		changeState(MonitorState.ON);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_SAVE, 0, "Copy").setIcon(android.R.drawable.ic_menu_save);
		menu.add(0, MENU_QUIT, 0, "Quit").setIcon(android.R.drawable.ic_menu_close_clear_cancel);		
		return super.onCreateOptionsMenu(menu);
	}


	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case MENU_SAVE:
			try {
				MySQLiteHelper.copyDataBase(context, SensorRecorder.DBNAME, 
						Environment.getExternalStorageDirectory() + "/" + SensorRecorder.DBNAME );
			} catch (Throwable e) {
				Log.d("Exception", e.toString());
				Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
			}
			return true;
		case MENU_QUIT:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	
	private void changeState(MonitorState state) {
		switch(state){
		case OFF:
			sm.unregisterListener(this);			
			break;
		case CHANGE_RATE:
			reregisterSensor();
			break;
		case CHANGE_SENSOR:
			reregisterSensor();
			changeState(MonitorState.CHANGE_DESCRIPTION);
			break;
		case ON:
			currentSensor =  ((SensorWrapper) spinner.getSelectedItem()).getSensor();
			currentRate =  ((SensorRate) rateSpinner.getSelectedItem()).getValue();
			reregisterSensor();
			changeState(MonitorState.CHANGE_DESCRIPTION);
			break;
		case CHANGE_DESCRIPTION:
			textViewName.setText(currentSensor.getName());
			textViewPower.setText("" + currentSensor.getPower());
			textViewResolution.setText("" + currentSensor.getResolution());
			textViewMaxRange.setText("" + currentSensor.getMaximumRange());			
			break;
		}
	}


	private void reregisterSensor() {
		sm.unregisterListener(this);
		sm.registerListener(this, currentSensor, currentRate);
	}
	
	
	private void changeState(RecorderState newState) {
		switch(newState){
		case START:
			recorder = new SensorRecorder(context, sm, currentSensor, currentRate);
			recorder.start();
			changeState(RecorderState.RUNNING);
			acquireLock();
			break;
		case RUNNING:
			button.setText("Stop");
			button.setOnClickListener(stopListener);
			break;
		case STOP:
			if (recorder != null)
			{
				recorder.stop();
				Toast.makeText(context, "Gathered samples:" + recorder.getNumberOfSamples(), Toast.LENGTH_SHORT).show();
			}
			
			changeState(RecorderState.STOPPED);
			releaseLock();
			break;
		case STOPPED:
			recorder = null;
			button.setText("Start");
			button.setOnClickListener(startListener);		
			break;
		}

	}


	private void releaseLock() {
		if (wakeLock.isHeld())
			wakeLock.release();
	}


	private void acquireLock() {
		int lockType = ((WakeLockType) wakeLockSpinner.getSelectedItem()).getValue();
		wakeLock = pm.newWakeLock(lockType, "Sensors app");
		wakeLock.acquire();
	}
	

}