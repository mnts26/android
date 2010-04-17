package com.basior.learning;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;

public class Main extends Activity implements OnClickListener, OnLongClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        registerOnClick();
        
    }

	private void registerOnClick() {
		ImageView im = (ImageView) findViewById(R.id.ImageView01);
        im.setOnClickListener(this);
        
        im = (ImageView) findViewById(R.id.ImageView02);
        im.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		setContentView(R.layout.second);
		ImageView im = (ImageView) findViewById(R.id.ImageView03);
		
		if (v.getId() == R.id.ImageView01)
			im.setImageResource(R.drawable.marzena);
		else
			im.setImageResource(R.drawable.marzena2);
		
		im.setOnLongClickListener(this);
	}

	@Override
	public boolean onLongClick(View v) {
		setContentView(R.layout.main);
        registerOnClick();		
		return false;
	}
}