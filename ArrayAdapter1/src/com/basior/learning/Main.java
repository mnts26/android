package com.basior.learning;


import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.MonthDisplayHelper;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Main extends ListActivity {
    /** Called when the activity is first created. */
	
	String [] items = "STY LUT MAR KWI MAJ CZE LIP SIE WRZ PAZ LIS GRU".split(" ");
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items) ;                
        setListAdapter(adapter);
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		TextView t = (TextView) findViewById(R.id.TextView01);
		t.setText(items[position]);
	}

    
}