package com.basior.learning;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity {
	Context context;
	View customToast;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        
        Button b = (Button) findViewById(R.id.ButtonShort);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Quick toast!", Toast.LENGTH_SHORT).show();
			}
		});
        
        b = (Button) findViewById(R.id.ButtonLong);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "LONG TOAST!", Toast.LENGTH_LONG).show();
			}
		});
        
        
        b = (Button) findViewById(R.id.ButtonCustom);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast toast = new Toast(context);
				toast.setView(customToast);
				toast.show();
			}
		});
     
        customToast = getLayoutInflater().inflate(R.layout.custom, null);
    }
}