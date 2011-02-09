package com.basior;

import java.util.Arrays;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;
import android.widget.RemoteViews.RemoteView;

public class ConfigActivity extends Activity {//PreferenceActivity {
	
	int widgetId;
	
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		// TODO Put your code here
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
		    widgetId= extras.getInt(
		            AppWidgetManager.EXTRA_APPWIDGET_ID, 
		            AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		// if user cancels before reaching the end
		setResult(RESULT_CANCELED);
		
		Button b = (Button) findViewById(R.id.button_style_1);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				prepareRemoteView(R.layout.widget_style_1);
			}
		});
		
		b = (Button) findViewById(R.id.button_style_2);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				prepareRemoteView(R.layout.widget_style_2);
			}
		});
	}

	protected void prepareRemoteView(int layout) {
		WidgetProvider.updateWidget(this, widgetId); 
		
		Toast.makeText(this, "Created id:" + widgetId, Toast.LENGTH_SHORT).show();
		
		Intent result = new Intent();
		result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
		setResult(RESULT_OK, result);
		finish();
			
	}
}
