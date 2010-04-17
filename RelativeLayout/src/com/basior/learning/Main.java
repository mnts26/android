package com.basior.learning;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Main extends Activity  implements OnClickListener, OnLongClickListener{
    /** Called when the activity is first created. */
	
	static int currentLayout = R.layout.main;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(currentLayout);
    }

	private void setLayout(int newLayout) {
		currentLayout = newLayout;
		setContentView(currentLayout);
		
		if (currentLayout == R.layout.main)
		{
			Button b = (Button) findViewById(R.id.Ok);
	        b.setOnClickListener(this);
		}
		else
		{
	        ImageView i = (ImageView) findViewById(R.id.ImageView01);
	        i.setOnLongClickListener(this);	
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.Cancel)
		{
			// nothing
		}
		else
		{
			setLayout(R.layout.second);			
		}
	}

	@Override
	public boolean onLongClick(View v) {
		setLayout(R.layout.main);
		return false;
	}
}