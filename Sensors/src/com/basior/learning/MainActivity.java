package com.basior.learning;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity implements SensorEventListener {
    private static final int MENU_SAVE = Menu.FIRST;
	private static final int MENU_QUIT = MENU_SAVE + 1;
	/** Called when the activity is first created. */
	
	Spinner spinner;
	Spinner rateSpinner;
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
	static SensorRunner runner = null;
	
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
		
		setupRateSpinner();
		setupSensorSpinner();
		setupButton();
    }


	private void setupButton() {
		button = (Button)findViewById(R.id.ButtonStartStop);
		if (runner != null)
		{
			button.setText("Stop");
			button.setOnClickListener(stopListener);
		}
		else
		{
			button.setText("Start");
			button.setOnClickListener(startListener);
		}
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
			unregisterSensor();		
			registerSensor();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private final OnItemSelectedListener sensorSpinnerListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Sensor s = sensors.get(position).getSensor();
			
			textViewName.setText(s.getName());
			textViewPower.setText("" + s.getPower());
			textViewResolution.setText("" + s.getResolution());
			textViewMaxRange.setText("" + s.getMaximumRange());
			
			if (s == currentSensor)
				return;
				
			unregisterSensor();		
			currentSensor = s;		
			registerSensor();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};
	
	private final View.OnClickListener startListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			runner = new SensorRunner(context, sm, currentSensor, currentRate);
			startRunner();
			button.setText("Stop");
			button.setOnClickListener(stopListener);
		}
	};
	
	private final View.OnClickListener stopListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			stopRunner();
			Toast.makeText(context, "Gathered samples:" + runner.getNumberOfSamples(), Toast.LENGTH_SHORT).show();
			runner = null;
			button.setText("Start");
			button.setOnClickListener(startListener);
		}
	};

	private void registerSensor() {
		if (currentSensor != null)
		{
			sm.registerListener(this, currentSensor, currentRate);
		}
	}

	private void startRunner() {
		if (runner != null)
			runner.start();
	}


	private void unregisterSensor() {
		if (currentSensor != null)
		{
			sm.unregisterListener(this);
		}
	}

	private void stopRunner() {
		if (runner != null)
			runner.stop();
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
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
		unregisterSensor();
		stopRunner();
	}


	@Override
	protected void onResume() {
		super.onResume();
		registerSensor();
		startRunner();
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
				MySQLiteHelper.copyDataBase(context, SensorRunner.DBNAME, Environment.getExternalStorageDirectory() + "/" + SensorRunner.DBNAME );
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


	
	

}