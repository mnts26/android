package com.basior.learning;

import android.hardware.Sensor;

public class SensorWrapper {

	Sensor sensor;

	public SensorWrapper(Sensor sensor) {
		super();
		this.sensor = sensor;
	}

	@Override
	public String toString() {
		return sensor.getName();
	}

	public Sensor getSensor() {
		return sensor;
	}
	
	
	
}
