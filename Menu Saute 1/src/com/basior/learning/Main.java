package com.basior.learning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Main extends Activity {
	
	Menu menu = null;
	List<MenuData> menuData = new ArrayList<MenuData>();
    /** Called when the activity is first created. */
	public Main()
	{
		menuData.add(new MenuData(Menu.FIRST+1, android.R.drawable.ic_menu_gallery, "Gallery"));
		menuData.add(new MenuData(Menu.FIRST+2, android.R.drawable.ic_menu_send, "Send"));
		menuData.add(new MenuData(Menu.FIRST+3, android.R.drawable.ic_menu_search, "Search"));
		menuData.add(new MenuData(Menu.FIRST+4, android.R.drawable.ic_menu_zoom, "Zoom"));
		menuData.add(new MenuData(Menu.FIRST+5, android.R.drawable.ic_menu_view, "View"));
		menuData.add(new MenuData(Menu.FIRST+6, android.R.drawable.ic_menu_share, "Share"));
		menuData.add(new MenuData(Menu.FIRST+7, android.R.drawable.ic_menu_agenda, "Agenda"));
		
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.menu = menu;

		for (MenuData data : menuData) {
			menu.add(0, data.getId(), 0, data.getName()).setIcon(data.getResourceImageId());
		}
			
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		TextView text = (TextView) findViewById(R.id.TextView);
		text.setText(item.getTitle());
		return super.onOptionsItemSelected(item);
	}
	
	
}
    
    
