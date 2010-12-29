package com.pogoda;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import android.sax.RootElement;
import android.util.Xml;

public class BasicSAXParser<T> implements ListProvider<T> {

	private final Reader reader;
	private final ContentProvider<T> contentProvider;
	private boolean parsed = false;
	
	public BasicSAXParser(Reader reader, ContentProvider<T> contentProvider ) {
		super();
		this.reader = reader;
		this.contentProvider = contentProvider;
	}

	private void parse() {
		try {
			Xml.parse(reader, contentProvider.getContentHandler());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<T> getList() {
		if (!parsed) {
			parse();
			parsed = true;
		}
		return contentProvider.getList();
	}


}
