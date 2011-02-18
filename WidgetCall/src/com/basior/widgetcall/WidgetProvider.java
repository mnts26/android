package com.basior.widgetcall;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		WidgetCreator wc = new WidgetCreator();
		for (int i : appWidgetIds) {
			
			appWidgetManager.updateAppWidget(i, wc.getViews(context,i));
		}
		
	}

	
	
}