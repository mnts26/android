package com.basior.learning;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class Main extends Activity implements OnClickListener{
	
	static int scaleType = 0;
	final static int SCALES = ScaleType.values().length; 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView b = (ImageView) findViewById(R.id.ImageView01);
        b.setOnClickListener(this);
        changeScale(b, scaleType);
    }

    
	@Override
	public void onClick(View v) {
		ImageView b = (ImageView) v;
		changeScale(b, ++scaleType);
	}


	private void changeScale(ImageView b, int scale) {
		b.setScaleType(ScaleType.values()[scale% SCALES]);
		updateText(b.getScaleType().toString() + ": " + scaleType % SCALES + " / " + SCALES);
	}

	private void updateText(String text) {
		TextView t = (TextView) findViewById(R.id.TextView01);
		t.setText(text);
	}
}