package com.basior.learning;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class Main extends Activity {
    /** Called when the activity is first created. */
	private Context context;
	Calendar calendar = new GregorianCalendar();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        setText();
        
        Button b = (Button) findViewById(R.id.ButtonDAte);
        b.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
				new DatePickerDialog(context, dateListener, 
						calendar.get(Calendar.YEAR), 
						calendar.get(Calendar.MONTH), 
						calendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

        b = (Button) findViewById(R.id.ButtonTime);
        b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new TimePickerDialog(context, timeListener, 
						calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
			
			}
		});
    }
    
    DatePickerDialog.OnDateSetListener dateListener = new  DatePickerDialog.OnDateSetListener() {		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, monthOfYear);
			calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			setText();
		}
	};
    
	TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(Calendar.MINUTE, minute);
				setText();
		}
	};
	
    private void setText()
    {
    	TextView text = (TextView) findViewById(R.id.TextView01);
    	java.text.DateFormat d = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.FULL, java.text.DateFormat.FULL, Locale.getDefault());
    	text.setText(d.format(calendar.getTime()));
    }
}