package com.basior.learning;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter.ViewBinder;

public class Now extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        Button btn = (Button) findViewById(R.id.Button01);
        btn.setOnClickListener(this);
        updateTime();
    }

	private void updateTime() {
		Button btn = (Button) findViewById(R.id.Button01);
		btn.setText(new Date().toString() + "DUPA");
	}

	@Override
	public void onClick(View v) {
		updateTime();
		
	}
}