package com.basior.learning;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Main extends Activity {
    /** Called when the activity is first created. */
	String [] items = Locale.getISOLanguages();
	static boolean [] states = new boolean[Locale.getISOLanguages().length];
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ArrayAdapter<String> adapter = new CustomAdapter<String>(this, android.R.layout.simple_list_item_1, items);
       
        ListView lv = (ListView) findViewById(R.id.ListView01);
        lv.setAdapter(adapter);
        lv.setTextFilterEnabled(true);
    }
    
    class CustomAdapter<T> extends ArrayAdapter<T> implements OnCheckedChangeListener {
    	//Conte
		public CustomAdapter(Context context, int textViewResourceId, T[] items) {
			super(context, textViewResourceId, items);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null)
			{
				convertView = getLayoutInflater().inflate(R.layout.mycontrol, null);
			}
			
			RelativeLayout layout = (RelativeLayout) convertView;
			
			for (int i = 0; i < layout.getChildCount(); i++)
			{
				if (layout.getChildAt(i).getId() == R.id.TextView01)
				{
					TextView t = (TextView) layout.getChildAt(i);
			//		if (t != null)
						t.setText(items[position]);		
				}
				else if (layout.getChildAt(i).getId() == R.id.CheckBox01)
				{
					CheckBox c = (CheckBox) layout.getChildAt(i);
					c.setTag(position);
					c.setChecked(states[position]);
					c.setOnCheckedChangeListener(this);
				}
			}
			return convertView;
		}

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean newState) {
			CheckBox c = (CheckBox) arg0;
			states[(Integer)c.getTag()] = newState;
			
		}
		
		
		
		
		
		
    	
    }
}