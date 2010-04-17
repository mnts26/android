package com.basior.learning;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TableLayout l = new TableLayout(this);
        TextView t = new TextView(this);
        t.setText("Tabela:");
        l.addView(t);
        
        
        String numbers [][] = {{ "7", "8", "9"}, { "4", "5", "6"}, { "1", "2", "3"} };
        for (String[] row : numbers) {
        	TableRow tb = new TableRow(this);
        	//tb.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
        	l.addView(tb);
			for (String number : row) {
				Button b = new Button(this);
				b.setLayoutParams(new LayoutParams(5,5));
				b.setText(number);
				tb.addView(b);
			}
		}

        setContentView(l);
    }
}