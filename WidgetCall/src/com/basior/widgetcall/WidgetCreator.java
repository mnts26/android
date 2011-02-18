package com.basior.widgetcall;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;

public class WidgetCreator {

	private static final String DEFAULT_LIMIT = "TAP";
	private static final String LIMIT_LEFT = "Limit left";
	private static final String PREFERENCES_NAME = "com.basior.widgetCall.preferences";

	public RemoteViews getViews(Context context, int widgetId) {
		
		RemoteViews rm = new RemoteViews(context.getPackageName(), R.layout.widget_initial_layout);
		
		Intent intent = new Intent(context, ServiceDialer.class);
		intent.setData(Uri.withAppendedPath(Uri.parse("my/widget/id/"), String.valueOf(widgetId)));
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		String text = getText(context);
		
		rm.setTextViewText(R.id.TextView, text);
		rm.setOnClickPendingIntent(R.id.TextView, pendingIntent);
		rm.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
		
		return rm;
	}

	private String getText(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME, context.MODE_PRIVATE);
		return prefs.getString(LIMIT_LEFT, DEFAULT_LIMIT);
	}

	
}
