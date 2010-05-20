package com.basior.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Main extends Activity {
	
	Integer[] samples = new Integer[] { 128,512,1024,65536 };
	EditText editTimes;
	TextView equal;
	Spinner spinner;
	ListView log;
	SimpleAdapter logAdapter;
	Button buttonRun;
	
	List<Map<String,Object>> logData = new ArrayList<Map<String,Object>>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        buttonRun = (Button) findViewById(R.id.ButtonRun);
        buttonRun.setOnClickListener(buttonListener);
        
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, samples);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinner = (Spinner) findViewById(R.id.Spinner01);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(spinnerListener);
        
        equal = (TextView) findViewById(R.id.TextViewEqual);
        
        editTimes = (EditText)findViewById(R.id.EditTextTimes);
        editTimes.setOnKeyListener(timesKeyListener);
        
        log = (ListView) findViewById(R.id.ListView01);
        logAdapter = new SimpleAdapter(this, logData, R.layout.row, 
        		new String[] {"Name", "Sum", "Time"}, 
        		new int[] {R.id.TextViewName, R.id.TextViewSum, R.id.TextViewTime});
        log.setAdapter(logAdapter);
    }    
    
    private View.OnKeyListener timesKeyListener = new View.OnKeyListener() {			
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			updateEqual();
			if (editTimes.getText().length() == 4 && isDecimal(keyCode))
				return true; // consume event
			return false;
		}

		private boolean isDecimal(int keyCode) {
			if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9)
				return true;
			return false;
		}
	};

	private AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			updateEqual();
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {}
	};
	 
	
    	
	private void updateEqual() {
		try {
		int loops = Integer.parseInt(editTimes.getText().toString());
		int sample = (Integer) spinner.getSelectedItem();
		equal.setText("=" + loops*sample);
		} catch (Throwable e){
			equal.setText("=?");
		}
	}
	
	
    View.OnClickListener buttonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			logData.clear();
			buttonRun.setEnabled(false);
			int loops = Integer.parseInt(editTimes.getText().toString());
			int sample = (Integer) spinner.getSelectedItem();
			
			MyHandler handler = new MyHandler();
			handler.toDo.add(new Float2(handler, sample, loops));
			handler.toDo.add(new Int1(handler, sample, loops));
			handler.toDo.add(new Int2(handler, sample, loops));
			handler.post(new RunnableProxy(new Float1(handler, sample, loops)));
		}
	};
	
	
	class MyHandler extends Handler {
		public List<AbstractCalculator> toDo = new ArrayList<AbstractCalculator>();
		
		@Override
		public void handleMessage(Message msg) {
			Bundle data = msg.getData();
			
			Map<String,Object> logItem = new HashMap<String, Object>();
			logItem.put("Name", data.getString("Algorithm"));
			logItem.put("Sum", data.getFloat("Sum"));
			logItem.put("Time", data.getLong("Time"));
			
			
			logData.add(logItem);
			logAdapter.notifyDataSetChanged();
			if (toDo.size() > 0)
			{
				post(new RunnableProxy(toDo.remove(0)));
			}
			else
			{
				buttonRun.setEnabled(true);
			}
		}
	};
    
}