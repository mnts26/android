package aexp.customlist;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomList extends ListActivity
{
	ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	private static final int ADD_ITEM_ID = 1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle)
    {
       super.onCreate(icicle);
       setContentView(R.layout.main);
       notes = new SimpleAdapter( 
				this, 
				list,
				R.layout.main_item_two_line_row,
				new String[] { "line1","line2" },
				new int[] { R.id.text1, R.id.text2 }  );
       setListAdapter( notes );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      boolean result = super.onCreateOptionsMenu(menu);
      menu.add(0, ADD_ITEM_ID, Menu.NONE, R.string.add_item );
      return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
          case ADD_ITEM_ID:
				addItem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

	private void addItem() {
	  long ts = System.currentTimeMillis();
	  int lastDigit = (int)( ts % 10 );
	  HashMap<String,String> item = new HashMap<String,String>();
	  item.put( "line1",Long.toString( ts ) );
	  item.put( "line2","lastDigit: "+Integer.toString( lastDigit ) );
	  list.add( item );
      notes.notifyDataSetChanged();
	}

    private SimpleAdapter notes;
}
