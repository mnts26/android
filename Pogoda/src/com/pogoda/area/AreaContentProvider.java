package com.pogoda.area;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;

import android.sax.Element;
import android.sax.RootElement;
import android.sax.StartElementListener;

import com.pogoda.ContentProvider;

public class AreaContentProvider implements ContentProvider<Area> {

	List<Area> list;
	
	
	@Override
	public List<Area> getList() {
		return list;
	}

	@Override
	public ContentHandler getContentHandler() {
		RootElement root = new RootElement("root");
		Element item = root.getChild("area");
		root.setStartElementListener(new StartElementListener() {
			
			@Override
			public void start(Attributes arg0) {
				list = new ArrayList<Area>();
			}
		});
		
		item.setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				Area area = new Area();
				area.setCode(attributes.getValue("code"));
				area.setName(attributes.getValue("name"));
				list.add(area);
			}
		});
		
		return root.getContentHandler();
	}

}
