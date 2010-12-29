package com.pogoda.letters;

import java.io.InputStreamReader;
import java.util.List;

import com.pogoda.ListAcceptor;
import com.pogoda.R;
import com.pogoda.diagram.DiagramActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LetterActivity extends Activity implements ListAcceptor<Letter> {
	
	ProgressDialog dialog = null;
	ListView listview = null;
	ArrayAdapter<Letter> adapter = null;
	Context ctx;
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
		setContentView(R.layout.simple_list);
		listview = (ListView) findViewById(R.id.ListView01);

		listview.setOnItemClickListener(listener);		
		
		dialog = new ProgressDialog(this);
		dialog.setTitle(R.string.chooseLetter);
		dialog.setMessage(getString(R.string.wait));
		dialog.show();
		
		LetterAsyncTask task = new LetterAsyncTask(this);
		task.execute(new InputStreamReader(getResources().openRawResource(R.raw.letters)));
		 
	}
	
	@Override
	public void accept(List<Letter> list) {
		dialog.cancel();
		adapter = new ArrayAdapter<Letter>(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
	}
	
	OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> ada, View arg1, int pos,
				long arg3) {
			Letter letter = (Letter) adapter.getItem(pos);			
			Intent intent = new Intent(ctx, DiagramActivity.class );
			intent.putExtra("letter.code", letter.getCode());
			intent.putExtra("letter.name", letter.getName());
			intent.putExtra("area.code", getIntent().getExtras().getString("area.code"));
			intent.putExtra("area.name", getIntent().getExtras().getString("area.name"));
			startActivity(intent);
		}
	};
}
