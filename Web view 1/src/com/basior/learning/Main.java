package com.basior.learning;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;

public class Main extends Activity {
	WebView webview;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        webview = (WebView) findViewById(R.id.webkit);
        webview.setWebViewClient(new WebViewClient()
        {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.equals("time"))
				{
					showHtml("Time is the most precious thing we can have - seriously.... I mean it!!!");
					return true; // we've already handled the request
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
        });
        
        showTime();
        
        Button b = (Button) findViewById(R.id.Button01);
        b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showTime();
			}
		});
        
       
        b = (Button) findViewById(R.id.Button02);
        b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				webview.loadUrl("http://www.onet.pl");
			}
		});
        
       
    }
    
	private void showTime() {		
		String time = new Date().toString();
		
		time = "<html>" +
				"<head>" +
				"<body>" +
					"<B>Current time:</B>" +  "<a href =\"time\">" + time + "</a>" +
				"</body>" +
				"</head>" +
				"</html>";
		
		showHtml(time);
	}

	private void showHtml(String time) {
		webview.loadData(time, "text/html", "UTF-8");
	}
	
	 
}


