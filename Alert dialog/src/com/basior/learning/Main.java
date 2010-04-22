package com.basior.learning;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends Activity implements android.view.View.OnClickListener {
	
	ImageView image;
	TextView text;
	Button button;
	Builder builder;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
          
        image = (ImageView)findViewById(R.id.ImageView01);
        text = (TextView) findViewById(R.id.TextView01);
        button = (Button) findViewById(R.id.Button01);
        button.setOnClickListener(this);
        builder = getDialog();
		}
    
	@Override
	public void onClick(View arg0) {
		button.setText("Try again");
        builder.show();
		
	}

	private Builder getDialog() {
		return 
		
		new AlertDialog.Builder(this).setTitle("Question")
        .setMessage("Do you like to go to work?")
        .setPositiveButton("YES YES YES", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				image.setImageResource(R.drawable.worker_wires);
				text.setText("This is what is going to happen");
			}
		})
		.setNeutralButton("Hmmm", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				image.setImageResource(R.drawable.worker_girl);
				text.setText("Are you sure? Sometimes nice things happen");
			}
		})
		.setNegativeButton("Of course no", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				image.setImageResource(R.drawable.plaza);
				text.setText("Correcto mondo :)");
			}
		})
		.setIcon(android.R.drawable.ic_dialog_alert);
	}
}
