package com.pogoda.area;

import java.io.InputStreamReader;
import java.util.List;

import com.pogoda.ListAcceptor;
import com.pogoda.R;
import com.pogoda.letters.LetterActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AreaActivity extends Activity implements ListAcceptor<Area> {
	
	ProgressDialog dialog = null;
	ListView listview = null;
	ArrayAdapter<Area> adapter = null;
	Context ctx;
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.simple_list);
		listview = (ListView) findViewById(R.id.ListView01);
		listview.setOnItemClickListener(listener);
		dialog = new ProgressDialog(this);
		dialog.setTitle(R.string.chooseArea);
		dialog.setMessage(getString(R.string.wait));
		dialog.show();
		
		AreaAsyncTask task = new AreaAsyncTask(this);
		task.execute(new InputStreamReader(getResources().openRawResource(R.raw.areas)));
		
		ctx = this;
		
	}
	
	@Override
	public void accept(List<Area> list) {
		dialog.cancel();
		adapter = new ArrayAdapter<Area>(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
	}
	

	private OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapter, View arg1, int pos,
				long id) {
			Area area = (Area) adapter.getItemAtPosition(pos);
			Intent intent = new Intent(ctx, LetterActivity.class);
			intent.putExtra("area.code", area.getCode());
			intent.putExtra("area.name", area.getName());
			startActivity(intent);
		}
	};
}
