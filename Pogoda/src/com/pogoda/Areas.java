package com.pogoda;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import android.text.Html;

public class Areas extends Observable implements ListProvider<String> {

	String xml;
	List<String> list;
	
	
	
	public String getXml() {
		return xml;
	}


	public void setXml(String xml) {
		this.xml = xml;
		int start= xml.indexOf("<select");
		int end = xml.indexOf("</select>", start) + "</select>".length();
		
		String content = xml.substring(start, end);
		list = Arrays.asList(Html.fromHtml(content).toString().split(" "));
	}

	public void completed() {
		setChanged();
		notifyObservers(this);
	}


	@Override
	public List<String> getList() {
		return list;
	}
}
