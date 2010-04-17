package com.basior.learning;

import com.basior.learning.CustomList.ImageResourceProvider;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayAdapter<ImageResourceProvider> adapter = new ArrayAdapter<ImageResourceProvider>(this, android.R.layout.simple_list_item_1);
        
        for (int i = 0; i < 10; i++)
        {
        	DataItem data = new DataItem(android.R.drawable.ic_menu_add, "Item " + (i+1));
        	adapter.add(data);	
        }
        
        
        ListView list = (ListView) findViewById(R.id.ListView01);
        list.setAdapter(adapter);
        
        
    }
}