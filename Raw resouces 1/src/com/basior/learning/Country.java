package com.basior.learning;
public class Country {
	private String name;
	private String code;

	@Override
	public String toString() {
		return "[code=" + code + ", name=" + name + "]";
	}

	public Country(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
	
	

}
