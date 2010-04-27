package com.basior.learning;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Main extends Activity implements OnClickListener {
	private static final String PREFS_FILTER = "filter";
	private static final String PREFS_SCROLL_Y = "scroll Y";
	EditText filterText;
	ArrayList<Country> countries = getCountries();
	MyAdapter<Country> adapter;
	ListView list;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		       
        countries = getCountries();
        adapter = new MyAdapter<Country>(this, android.R.layout.simple_list_item_1);
		list = (ListView) findViewById(R.id.ListView01);
		list.setAdapter(adapter);
		filterCountries("");
		
		Button search = (Button) findViewById(R.id.ButtonSearch);
		search.setOnClickListener(this);
		
		filterText = (EditText) findViewById(R.id.EditText01);
    }

	private ArrayList<Country> getCountries() {
		ArrayList<Country> countries = new ArrayList<Country>();
        
        try {
        	InputStream in = getResources().openRawResource(R.raw.countries);
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(in);
			NodeList list = doc.getElementsByTagName("country");
			for (int i = 0; i < list.getLength(); i++)
			{
				Element e = (Element) list.item(i);
				countries.add(new Country(getCharacterDataFromElement(e), e.getAttribute("iso2")));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return countries;
	}
	
	 public static String getCharacterDataFromElement(Element e) {
		   Node child = e.getFirstChild();
		   if (child instanceof CharacterData) {
		     CharacterData cd = (CharacterData) child;
		       return cd.getData();
		     }
		   return "?";
		 }

		private void filterCountries(String filter) {
			adapter.clear();
			for (Country country : countries) 
			{
				if (country.getName().toLowerCase().startsWith(filter))
				{
					adapter.add(country);
				}
			}
		}
		
	class MyAdapter<T> extends ArrayAdapter<T>
	{

		public MyAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null)
			{
				convertView = getLayoutInflater().inflate(R.layout.listitem, null);
				convertView.setTag(new ViewWrapper((ViewGroup) convertView));
			}
			
			ViewWrapper wrapper = (ViewWrapper) convertView.getTag();
			Country country = (Country) getItem(position);
			wrapper.getCode().setText(country.getCode());
			wrapper.getCountry().setText(country.getName());
			
			return convertView;
		}
		
		
	}

	@Override
	public void onClick(View arg0) {
		String filter = filterText.getText().toString();
		filterCountries(filter);
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences.Editor editor = getPreferences(0).edit();
		editor.putString(PREFS_FILTER, filterText.getText().toString());
		editor.putInt(PREFS_SCROLL_Y, list.getScrollY());
		editor.commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences prefs = getPreferences(0);
		filterText.setText(prefs.getString(PREFS_FILTER, ""));
		filterCountries(filterText.getText().toString());
		list.scrollBy(0, prefs.getInt(PREFS_SCROLL_Y, 0));
	}

	

}