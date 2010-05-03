package com.basior.learning;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.ConsumeEventLeft).setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				originalOnTouch(v, event);
		        return true; // consume touch even 
			}


		});
        
        findViewById(R.id.ConsumeEventRight).setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				originalOnTouch(v, event);
				return true;
			}
		});
        
       findViewById(R.id.Button01).setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			hideKeyboard(findViewById(R.id.InputManagerLeft)); 
		    //hideKeyboard(findViewById(R.id.InputManagerRight));
		}
	}); 
       
    }

	private void hideKeyboard(View v) {
		InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        
	}
    
	private void originalOnTouch(View v, MotionEvent event) {
		EditText MyEditor = (EditText) v;
        int inType = MyEditor.getInputType(); // backup the input type
        MyEditor.setInputType(InputType.TYPE_NULL); // disable soft input
        MyEditor.onTouchEvent(event); // call native handler
        MyEditor.setInputType(inType); // restore input type
	}
}