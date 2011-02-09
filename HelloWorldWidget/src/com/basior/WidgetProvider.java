package com.basior;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.method.DateTimeKeyListener;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class WidgetProvider extends AppWidgetProvider {

	public static final String MY_REFRESH = "777";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (intent.getAction().equals(MY_REFRESH)) {			
		    int widgetId = intent.getExtras().getInt(
		            AppWidgetManager.EXTRA_APPWIDGET_ID, 
		            AppWidgetManager.INVALID_APPWIDGET_ID);
		    
		    Toast.makeText(context, "Req up:" + widgetId, Toast.LENGTH_SHORT).show();
		    updateWidget(context, widgetId);
		}
		
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		
		Toast.makeText(context, "onUpdate" + Arrays.toString(appWidgetIds), Toast.LENGTH_SHORT).show();
		for (int i = 0; i < appWidgetIds.length; i++) {
			updateWidget(context, appWidgetIds[i]);
		}
	}

	static public void updateWidget(Context context,int widgetId) {
		AppWidgetManager mgr = AppWidgetManager.getInstance(context);
		RemoteViews views = getRemoteViews(context, widgetId);
		mgr.updateAppWidget(widgetId, views);
	}

	static private RemoteViews getRemoteViews(Context context, int widgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_style_1);
		
		String time = DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date());
		views.setTextViewText(R.id.textViewStyle1, time );
		
		Intent i = new Intent(context, WidgetProvider.class);
		i.setAction(WidgetProvider.MY_REFRESH);		
		i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
		// pojdzie jako broadcast
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);		
		views.setOnClickPendingIntent(R.id.LinearLayout1, pi);		    
	    
		return views;
	}

	
	
}