package com.pogoda.diagram;

import com.pogoda.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Xml.Encoding;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.TextView;

public class DiagramActivity extends Activity {
	
	ProgressDialog dialog = null;
	WebView webview = null;
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.diagram);
		
		StringBuilder title = new StringBuilder();
		title.append(getIntent().getExtras().getString("area.name"));
		title.append(':');
		title.append(getIntent().getExtras().getString("letter.code"));
		
		setTitle(title.toString());
//		TextView areaText = (TextView) findViewById(R.id.TextViewArea);
//		areaText.setText(getIntent().getExtras().getString("area.name"));
//		TextView letterText = (TextView) findViewById(R.id.TextViewLetter);
//		letterText.setText(getIntent().getExtras().getString("letter.name"));
		webview = new WebView(this);
		//webview = (WebView) findViewById(R.id.WebView01);
		setContentView(webview);
		
		dialog = new ProgressDialog(this);
		dialog.setTitle(R.string.chooseArea);
		dialog.setMessage(getString(R.string.wait));
		dialog.show();
		
		new DiagramAsyncTask(this).execute(null);
	}
	
	void completed(String webcontent) {
		webview.loadData("<html><body>Hello, world!</body></html>","text/html", "UTF-8");
		//webview.loadUrl("http://google.pl");
		dialog.cancel();
	}
}
