package com.basior.learning;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Main extends Activity implements OnCheckedChangeListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        RadioGroup rg = (RadioGroup)findViewById(R.id.RadioGroup01);
        rg.setOnCheckedChangeListener(this);
    }

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		TextView t = (TextView) findViewById(R.id.EditText01);
		RadioButton rb = (RadioButton)  findViewById(checkedId);
		t.setText("Wybrales: " + rb.getText());
		
	}
}