package com.pogoda;



import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements Observer {
    /** Called when the activity is first created. */
	ProgressDialog dialog = null;
	EditText text = null;
	ListView list = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        list = (ListView) findViewById(R.id.ListView01);
        
        dialog = new ProgressDialog(this);
        dialog.setTitle("Downloading");
        dialog.setMessage("Please wait a sec");
        dialog.show();
        
        new DownloadAreas(this).execute(null);
    }
    
  	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
		dialog.cancel();
		
		Areas areas = (Areas) arg1;
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, areas.getList());		
		list.setAdapter(adapter);
		
	}
}