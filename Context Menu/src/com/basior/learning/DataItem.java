package com.basior.learning;


public class DataItem  {
	int imageResource;
	String text;
	
	public DataItem(int imageResource, String text) {
		super();
		this.imageResource = imageResource;
		this.text = text;
	}

	public int getImageResourceId() {
		return imageResource;
	}

	@Override
	public String toString() {
		return text;
	}
}