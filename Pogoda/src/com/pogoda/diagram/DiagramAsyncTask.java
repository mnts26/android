package com.pogoda.diagram;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class DiagramAsyncTask extends AsyncTask<Void, Void, String> {

	DiagramActivity diagramActivity;
	
	public DiagramAsyncTask(DiagramActivity diagramActivity) {
		this.diagramActivity = diagramActivity;
	}

	@Override
	protected String doInBackground(Void... arg0) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://new.meteo.pl/um/php/gpp/next.php");
		//post.s
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "Nothing";
		try {
			responseBody = client.execute(post, responseHandler);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseBody;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		diagramActivity.completed(result);
		
	}

	
	
}
