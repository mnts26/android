package com.pogoda.letters;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;

import android.sax.Element;
import android.sax.RootElement;
import android.sax.StartElementListener;

import com.pogoda.ContentProvider;

public class LetterContentProvider implements ContentProvider<Letter> {

	List<Letter> list;
	
	
	@Override
	public List<Letter> getList() {
		return list;
	}

	@Override
	public ContentHandler getContentHandler() {
		RootElement root = new RootElement("root");
		Element item = root.getChild("letter");
		root.setStartElementListener(new StartElementListener() {
			
			@Override
			public void start(Attributes arg0) {
				list = new ArrayList<Letter>();
			}
		});
		
		item.setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				Letter letter = new Letter();
				letter.setCode(attributes.getValue("code"));
				letter.setName(attributes.getValue("name"));
				list.add(letter);
			}
		});
		
		return root.getContentHandler();
	}

}
