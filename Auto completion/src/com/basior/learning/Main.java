package com.basior.learning;

import java.util.Collections;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class Main extends Activity implements TextWatcher {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String []items = Locale.getISOLanguages();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView01);             
        actv.setAdapter(adapter);
        actv.addTextChangedListener(this);
    }

	@Override
	public void afterTextChanged(Editable s) {	
		
		Locale l = new Locale(s.toString());
		
		TextView t = (TextView) findViewById(R.id.TextView01);
		String text = "In " + Locale.getDefault().getDisplayName(Locale.ENGLISH) + ":" + l.getDisplayName(); 
		t.setText(text);

		t = (TextView) findViewById(R.id.TextView02);
		text = "In " + l.getDisplayName(Locale.ENGLISH) + ":" + l.getDisplayName(l);
		t.setText(text);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// 
		
	}
}