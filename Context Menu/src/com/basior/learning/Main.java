package com.basior.learning;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView.ScaleType;

public class Main extends Activity implements OnItemSelectedListener {
    /** Called when the activity is first created. */
	
	ArrayAdapter<DataItem> adapter;
	ImageView image;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        adapter = new ArrayAdapter<DataItem>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        for (DataItem item : getItems()) {
			adapter.add(item);
		}
        
        Spinner spinner = (Spinner) findViewById(R.id.Spinner01);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        
        
        image = (ImageView) findViewById(R.id.ImageView01);
        image.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				menu.setHeaderTitle("My menu");
				menu.add(0, Menu.FIRST+1, 0, "Send");
				menu.add(0, Menu.FIRST+2, 0, "Zoom in");
				menu.add(0, Menu.FIRST+3, 0, "Zoom out");
			}
		});
        
        image.setScaleType(ScaleType.CENTER_INSIDE);

    }

	private List<DataItem> getItems() {
		ArrayList<DataItem> result = new ArrayList<DataItem>();
		
		try {
			Class<?> clazz = Class.forName("com.basior.learning.R$drawable");
			for (Field f : clazz.getFields())
			{
				if (f.getName().equals("icon"))
				continue;
				
				String name = f.getName().replace('_', ' ');
				name = name.substring(0,1).toUpperCase() + name.substring(1);
				DataItem item = new DataItem(f.getInt(null), name);
				result.add(item);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) 
	{
		DataItem item = adapter.getItem(position);
		image.setImageResource(item.getImageResourceId());
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("dummy");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case Menu.FIRST+1:
			return true;
		case Menu.FIRST+2:
			image.setScaleType(ScaleType.CENTER);
			return true;
		case Menu.FIRST+3:
			image.setScaleType(ScaleType.CENTER_INSIDE);
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
}

