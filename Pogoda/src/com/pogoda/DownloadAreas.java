package com.pogoda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observer;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;


class DownloadAreas extends AsyncTask<Void, String, String> {

	private static final String AREAS_RAW = Environment.getExternalStorageDirectory() +"/areas.raw";
	private static final String ULR = "http://new.meteo.pl/um/php/gpp/search.php";
	Areas areas;
	
	
	
	public DownloadAreas(Observer observer) {
		super();
		areas = new Areas();
		if (observer != null)
			areas.addObserver(observer);
	}

	
	@Override
	protected String doInBackground(Void... arg0) {
		if (isCached(AREAS_RAW))
			return getContents(AREAS_RAW);
		
		return download();
	}


	private String download() {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(ULR);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "Nothing";
		try {
			responseBody = client.execute(get, responseHandler);
			FileWriter fw = new FileWriter(AREAS_RAW,true);
			fw.write(responseBody);
			fw.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseBody = e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("error", e.getMessage());
			e.printStackTrace();
			responseBody = e.getMessage();
		} finally {
			// shut down
		}
		return responseBody;
	}
	
	boolean isCached(String fileName) {
		File f = new File(fileName);
		return f.exists();
	}
	
	String getContents(String fileName) {
		BufferedReader br = null;
		StringBuffer buf = new StringBuffer();
		try {			
			br = new BufferedReader(new FileReader(fileName));
			String line = null;
			while ( (line = br.readLine()) != null) {
				buf.append(line);
				buf.append('\n');
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return buf.toString();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		areas.setXml(result);
		areas.completed();
	}

	
}
