package com.basior.learning;

public class DataItem  implements com.basior.learning.CustomList.ImageResourceProvider {
	int imageResource;
	String text;
	
	
	
	public DataItem(int imageResource, String text) {
		super();
		this.imageResource = imageResource;
		this.text = text;
	}



	@Override
	public int getImageResourceId() {
		return imageResource;
	}



	@Override
	public String toString() {
		return text;
	}

	
}
