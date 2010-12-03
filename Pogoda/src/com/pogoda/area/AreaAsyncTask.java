package com.pogoda.area;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.pogoda.BasicSAXParser;
import com.pogoda.ListAcceptor;
import com.pogoda.ListObserver;
import com.pogoda.ListProvider;

import android.os.AsyncTask;
import android.util.Log;

public class AreaAsyncTask extends AsyncTask<Reader, Void, Void> implements ListProvider<Area> {

	private ListObserver<Area> observer;
	private List<Area> result = null;
	
	public AreaAsyncTask(ListAcceptor<Area> acceptor) {
		observer = new ListObserver<Area>();
		observer.addAcceptor(acceptor);
	}
	
	@Override
	protected Void doInBackground(Reader... reader) {
		BasicSAXParser<Area> parser = new BasicSAXParser<Area>(reader[0], new AreaContentProvider());
		result = parser.getList();
		try {
			reader[0].close();
		} catch (IOException e) {
			Log.e(AreaAsyncTask.class.toString(), e.getMessage());
		}
		return null;
	}


	@Override
	protected void onPostExecute(Void list) {
		super.onPostExecute(list);
		observer.notify(this);
	}

	@Override
	public List<Area> getList() {
		return result;
	}

}
