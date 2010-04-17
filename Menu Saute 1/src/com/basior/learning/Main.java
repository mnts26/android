package com.basior.learning;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Main extends Activity {
	
	Menu menu = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
               
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.menu = menu;
		menu.add(0, Menu.FIRST+1,0, "TEST").setIcon(android.R.drawable.ic_menu_add);
		return super.onCreateOptionsMenu(menu);
	}
    
    
}