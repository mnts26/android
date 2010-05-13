package com.basior.learning;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity {
	Context context;
	EditText dbNameEdit;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.ButtonOpenOrCreate).setOnClickListener(createDatabase);
        findViewById(R.id.ButtonOpen).setOnClickListener(openDatabase);
        findViewById(R.id.ButtonDelete).setOnClickListener(deleteDatabse);
        
        dbNameEdit = (EditText)findViewById(R.id.EditText01);
        context = this;
    }

	private String getDbName() {
		return dbNameEdit.getText().toString();
	}
    
	private OnClickListener createDatabase = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//openOrCreateDatabase(getDbName(), MODE_PRIVATE, null);
			try {
				//SQLiteDatabase db = SQLiteDatabase.openDatabase(getDbName(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
				SQLiteDatabase db = openOrCreateDatabase(getDbName(), MODE_PRIVATE, null);
				Toast.makeText(context, "Path:" + db.getPath(), Toast.LENGTH_SHORT).show();
				db.close();
			}
			catch(Throwable e)
			{
				Toast.makeText(context, "Exception:" + e.toString(), Toast.LENGTH_SHORT).show();
			}
		}
	};
	protected String prefix = "/data/data/com.basior.learning/databases/q";
	
	private OnClickListener openDatabase = new OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				SQLiteDatabase db = SQLiteDatabase.openDatabase(prefix, null, SQLiteDatabase.OPEN_READWRITE);
				Toast.makeText(context, "Version:" + db.getVersion(), Toast.LENGTH_SHORT).show();
				db.close();
			}
			catch(Throwable e)
			{
				Toast.makeText(context, "Exception:" + e.toString(), Toast.LENGTH_SHORT).show();
			}
		}
	};
	private OnClickListener deleteDatabse = new OnClickListener() {
		@Override
		public void onClick(View v) {
			boolean isDeleted = deleteDatabase(getDbName());
			Toast.makeText(context, isDeleted ? "Deleted": "Not deleted", Toast.LENGTH_SHORT).show();
		}
	};
}
