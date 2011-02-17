package com.basior;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class WidgetProvider extends AppWidgetProvider {

	@Override
	public void onReceive(Context context, Intent intent) {
			super.onReceive(context, intent);
	}

	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		WidgetUpdater updater = new WidgetUpdater();
		for (int i = 0; i < appWidgetIds.length; i++) {
			Log.d("onUpdate", "Widget id:" + appWidgetIds[i]);
			updater.updateWidget(context, appWidgetIds[i]);
		}
	}



	
}