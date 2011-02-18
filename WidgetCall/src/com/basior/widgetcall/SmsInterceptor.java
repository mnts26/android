package com.basior.widgetcall;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsInterceptor extends BroadcastReceiver {
	private static final String MSG_KEYWORD = "Internet:";
	private static final String OPERATOR_NUMBER = "119120";
	private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";

	/**
	 * @see android.content.BroadcastReceiver#onReceive(Context,Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		if (!intent.getAction().equals(SMS_ACTION)) {
			// not for us
			return;
		}

		Bundle bundle = intent.getExtras();
		if (bundle == null) {
			return;
		}

		SmsMessage ussdReplay = null;
		for (SmsMessage message : getSmsMessages(bundle)) {
			if (message.getOriginatingAddress().equals(OPERATOR_NUMBER)) {
				ussdReplay = message;
			}
		}

		String msg = parseResponse(ussdReplay.getMessageBody());

		if (msg != null) {
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
			abortBroadcast();		
		}

	}


	private SmsMessage[] getSmsMessages(Bundle bundle) {
		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessages[] = new SmsMessage[messages.length];
		for (int n = 0; n < messages.length; n++) {
			smsMessages[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}
		return smsMessages;
	}

	private String parseResponse(String msg) {
		if (msg == null) {
			return null;
		}

		int start = msg.indexOf(MSG_KEYWORD);
		if (start >= 0) {
			return msg.substring(start + MSG_KEYWORD.length());
		} else {
			return null;
		}
	}
}
