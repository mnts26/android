package com.basior.learning;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.util.TimeFormatException;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
    /** Called when the activity is first created. */
	EditText text;
	EditText userText;
	Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        text = (EditText) findViewById(R.id.EditText01);
        userText = (EditText) findViewById(R.id.EditUser);
        context = this;
        setupButtons();      
        log("[Create]");
    }



	private void setupButtons() {
		
		Button b = (Button) findViewById(R.id.ButtonAlert);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				log("Alert");
				showLog();
				new AlertDialog.Builder(context).setMessage("And now?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
			}
		});
        
        b = (Button) findViewById(R.id.ButtonToast);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				log("Toast");
				showLog();
				Toast.makeText(context, "Happy toast!", Toast.LENGTH_SHORT).show();
			}
		});
        
        b = (Button)findViewById(R.id.ButtonLog);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				log(userText.getText().toString());
				showLog();
				userText.setText("");
			}
		});
        
        b = (Button)findViewById(R.id.ButtonClear);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clearLog();
				showLog();
			}


		});
        
        b = (Button) findViewById(R.id.ButtonClose);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
    


	private void log(String msg) {
		SharedPreferences pref = getPreferences(0);
		StringBuffer buf = new StringBuffer();
		
		buf.append(pref.getString("log", ""));		
		buf.append(msg);
        buf.append('\n');
        
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("log", buf.toString());
        editor.commit();
	}
	
	private void clearLog() {
		SharedPreferences.Editor editor = getPreferences(0).edit();
		editor.putString("log", "");
		editor.commit();
	}
	
	
	private void showLog() {
		SharedPreferences pref = getPreferences(0);
		text.setText(pref.getString("log", ""));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		log("[Restart]");
	}

	@Override
	protected void onStart() {
		super.onStart();
		log("[Start]");
	}

	@Override
	protected void onResume() {
		super.onResume();
		log("[Resume]");
		showLog();
	}
	
    @Override
	protected void onPause() {
		super.onPause();
		log("[Pause]");
	}

	
	@Override
	protected void onStop() {
		super.onStop();
		log("[Stop]");
	}
	
    @Override
	protected void onDestroy() {
		super.onDestroy();
		log("[Destroy]");
	}



	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		log("*Restore state*");
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("A", "B");
		log("*Save state*");
	}
    
    
    
    
    
}