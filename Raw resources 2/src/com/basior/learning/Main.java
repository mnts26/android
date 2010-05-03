package com.basior.learning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Main extends Activity {
    private static final String NON_SAVED_TEXT = "non_saved_text";
	private static final String FILENAME = "doc.txt";
	private static final int MENU_SAVE = Menu.FIRST;
	private static final int MENU_LOAD = Menu.FIRST +1;
	private static final int MENU_CLEAR = Menu.FIRST +2;
	private static final int MENU_DELETE = Menu.FIRST +3;
	private static final int MENU_BACK = Menu.FIRST +4;

	EditText editor;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        editor = (EditText)findViewById(R.id.EditText01);
        editor.setText("");
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, MENU_SAVE, 0, R.string.save).setIcon(android.R.drawable.ic_menu_save);
		menu.add(0, MENU_LOAD , 0, R.string.load).setIcon(android.R.drawable.ic_menu_revert);
		menu.add(0, MENU_CLEAR, 0, R.string.clear).setIcon(android.R.drawable.ic_input_delete);
		menu.add(0, MENU_DELETE, 0, R.string.delete).setIcon(android.R.drawable.ic_menu_delete);
		menu.add(0, MENU_BACK, 0, R.string.back).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.findItem(MENU_CLEAR).setVisible(editor.getText().length() > 0);
		menu.findItem(MENU_SAVE).setVisible(editor.getText().length() > 0);
		menu.findItem(MENU_DELETE).setVisible(fileExists(FILENAME));
		menu.findItem(MENU_LOAD).setVisible(fileExists(FILENAME));
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case MENU_SAVE:
			saveFile(FILENAME);
			return true;
		case MENU_LOAD:
			loadFile(FILENAME);
			return true;
		case MENU_CLEAR:
			editor.setText("");
			return true;
		case MENU_DELETE:
			//new AlertDialog.Builder(this).setTitle("Warning").setMessage("This operation is not supported yet").show();
			deleteFile(FILENAME);
			return true;
		case MENU_BACK:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	private void loadFile(String filename) {
		BufferedReader br = null;
		StringBuffer buf = new StringBuffer();
		try {
			InputStream in = openFileInput(filename);
			br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ( (line = br.readLine()) != null)
			{
				buf.append(line + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		if (buf.length() > 0)
			editor.setText(buf.toString());
	}

	private boolean fileExists(String filename) {
		for (String storedFileName : fileList()) {
			if (storedFileName.equalsIgnoreCase(filename)) 
				return true;
		}
		
		return false;
	}


	private void saveFile(String filename) {
		BufferedWriter writer = null;
		try {
			OutputStream os = openFileOutput(filename, MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(os));
			writer.write(editor.getText().toString());
		} catch (Throwable e) {
			new AlertDialog.Builder(this).setTitle("Exception").setMessage(e.toString()).show();
			e.printStackTrace();
		}
		finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		editor.setText(prefs.getString(NON_SAVED_TEXT, ""));
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		SharedPreferences.Editor prefsEditor = getPreferences(MODE_PRIVATE).edit();
		prefsEditor.putString(NON_SAVED_TEXT, editor.getText().toString());
		prefsEditor.commit();	
		super.onSaveInstanceState(outState);
	}
    
    
}