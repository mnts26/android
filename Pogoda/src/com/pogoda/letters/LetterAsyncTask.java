package com.pogoda.letters;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.pogoda.BasicSAXParser;
import com.pogoda.ListAcceptor;
import com.pogoda.ListObserver;
import com.pogoda.ListProvider;

import android.os.AsyncTask;
import android.util.Log;

public class LetterAsyncTask extends AsyncTask<Reader, Void, Void> implements ListProvider<Letter> {

	private ListObserver<Letter> observer;
	private List<Letter> result = null;
	
	public LetterAsyncTask(ListAcceptor<Letter> acceptor) {
		observer = new ListObserver<Letter>();
		observer.addAcceptor(acceptor);
	}
	
	@Override
	protected Void doInBackground(Reader... reader) {
		BasicSAXParser<Letter> parser = new BasicSAXParser<Letter>(reader[0], new LetterContentProvider());
		result = parser.getList();
		try {
			reader[0].close();
		} catch (IOException e) {
			Log.e(LetterAsyncTask.class.toString(), e.getMessage());
		}
		return null;
	}


	@Override
	protected void onPostExecute(Void list) {
		super.onPostExecute(list);
		observer.notify(this);
	}

	@Override
	public List<Letter> getList() {
		return result;
	}

}
