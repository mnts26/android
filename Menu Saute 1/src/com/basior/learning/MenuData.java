package com.basior.learning;

public class MenuData {

	int id;
	int resourceImageId;
	String name;
	
	
	
	public MenuData(int id, int resourceImageId, String name) {
		super();
		this.id = id;
		this.resourceImageId = resourceImageId;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public int getResourceImageId() {
		return resourceImageId;
	}
	public String getName() {
		return name;
	}
	
	
}
