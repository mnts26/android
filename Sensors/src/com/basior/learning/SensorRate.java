package com.basior.learning;

import android.hardware.SensorManager;

public enum SensorRate {

	UI( "UI", SensorManager.SENSOR_DELAY_UI),
	NORMAL ("NORMAL", SensorManager.SENSOR_DELAY_NORMAL),
	GAME ("GAME", SensorManager.SENSOR_DELAY_GAME),
	FASTEST ("FASTEST", SensorManager.SENSOR_DELAY_FASTEST);
	
	private SensorRate(String description, int value) {
		this.description = description;
		this.value = value;
	}

	private String description;
	private int value;
	
	@Override
	public String toString() {
	
		return description;
	}

	public int getValue() {
		return value;
	}
	
	
}
