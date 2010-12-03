package com.pogoda;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class RawXmlParser extends DefaultHandler implements ContentProvider<String> {

	private ArrayList<String> list = new ArrayList<String>();
	private StringBuilder builder;
	final private String tag;
	private boolean process = false;
	
	
	public RawXmlParser(String tag) {
		this.tag = tag;
	}
	
	@Override
	public List<String> getList() {
		return list;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if (localName.equals(tag)) {
			process = false;
			list.add(builder.toString());
			builder = new StringBuilder();
		}
		
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		builder = new StringBuilder();
		list = new ArrayList<String>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		try {
		super.startElement(uri, localName, qName, attributes);
		} catch (RuntimeException e) {
			// duck it
			Log.d("xml parser", e.getMessage());
		}
		if (localName.equals(tag)) {
			process = true;
		}
	}

	@Override
	public ContentHandler getContentHandler() {
		return this;
	}
	
	

	
}
