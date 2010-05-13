package com.basior.learning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
    /** Called when the activity is first created. */
	SQLiteDatabase db;
	MySQLiteOpenHelper helper;
	Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        helper = new MySQLiteOpenHelper(this);
        context = this;
        
        Button b = (Button)findViewById(R.id.ButtonInsert);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // set the format to sql date time
				Date date = new Date();
				ContentValues content = new ContentValues();
				content.put("my_date", dateFormat.format(date));
				long rowId = db.insert("times", null , content);
				Toast.makeText(context, "Row id:" + rowId, Toast.LENGTH_SHORT).show();
			}
		});
        
        b = (Button)findViewById(R.id.ButtonCopy);
        b.setOnClickListener(copyDatabase);
    }

    
	private final View.OnClickListener copyDatabase = new OnClickListener() {
		String inputfile = "/data/data/com.basior.learning/databases/times";
		
		@Override
		public void onClick(View v) {
			InputStream is = null;
			FileOutputStream fos = null;
			try {
				is = new FileInputStream(inputfile);
				fos = new FileOutputStream("/sdcard/times");
				byte[] buf = new byte[1024];
				int bytes = 0;
				while ((bytes = is.read(buf)) > 0) {
					fos.write(buf, 0, bytes);
				}
			} catch (FileNotFoundException e) {
				showException(e);
			} catch (IOException e) {
				showException(e);
			} finally {
				if (is != null)
					try {
						is.close();
					} catch (IOException e) {
						showException(e);
					}
				if (fos != null)
					try {
						fos.flush();
						fos.close();
					} catch (IOException e) {
						showException(e);
					}
			}

		}

		private void showException(Throwable e) {
			Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
		}
	};
		


	@Override
	protected void onPause() {
		super.onPause();
		db.close();
		db = null;		

	}

	@Override
	protected void onResume() {
		super.onResume();
		db = helper.getWritableDatabase();
	}
    
    
}
