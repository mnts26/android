package com.basior.learning;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.basior.learning.CustomList.ImageResourceProvider;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

	
public class Main extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
	ArrayAdapter<ImageResourceProvider> adapter;
    EditText edit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        adapter = new ArrayAdapter<ImageResourceProvider>(this, android.R.layout.simple_list_item_1);
        ListView list = (ListView) findViewById(R.id.ListView01);
        list.setAdapter(adapter);
        
        edit = (EditText) findViewById(R.id.EditText01);
        Button button = (Button) findViewById(R.id.Button01);
        button.setOnClickListener(this);
        
        
    }
    
	@Override
	public void onClick(View v) {
		adapter.clear();
		List<DataItem> data = getDataItems(edit.getText().toString());
		for (DataItem d : data)
		{
			adapter.add(d);	
		}
	}

	private List<DataItem> getDataItems(String fieldName) {
		ArrayList<DataItem> result = new ArrayList<DataItem>();
		try {
			Class<?> clazz = Class.forName("android.R$drawable");
			for (Field f :clazz.getFields())
			{
				if (f.getName().startsWith(fieldName))
				{
					result.add(new DataItem(f.getInt(null), f.getName()));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}