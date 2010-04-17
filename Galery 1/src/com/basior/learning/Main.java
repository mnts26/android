package com.basior.learning;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Gallery;

public class Main extends Activity {
    /** Called when the activity is first created. */
	
	String [] items = "A S D F G H J K L".split(" ");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Gallery g = (Gallery) findViewById(R.id.Gallery01);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, items);
        g.setAdapter(adapter);
    }
}