package com.basior.widgetcall;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ServiceDialer extends IntentService {

	private static final String URI_FORMAT = "%s";
	private static final String DEFAULT_USSD = "*121#";
	private static final String USSD_NUMBER = "USSD_number";
	private static final String PREFERENCES_NAME = "com.basior.widgetCall.preferences";
	
	public ServiceDialer() {
		super("ServiceDialer");
	}
	
	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.d("dialer", "handling message");
		Uri tel = Uri.parse("tel:" + Uri.encode(getTelephoneNumer()));
		Log.d("dialer", "The number is:" + tel.toString());
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(tel);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	private String getTelephoneNumer() {
		SharedPreferences prefs = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);		
		return String.format(URI_FORMAT, prefs.getString(USSD_NUMBER, DEFAULT_USSD));
	}

}
