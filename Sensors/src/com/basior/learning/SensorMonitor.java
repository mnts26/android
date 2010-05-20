package com.basior.learning;

import android.hardware.Sensor;
import android.hardware.SensorManager;

public class SensorMonitor {
	private SensorManager sm;
	private Sensor sensor;
	private int rate;
	
	
	private SensorMonitor(SensorManager sm, Sensor sensor, int rate) {
		super();
		this.sm = sm;
		this.sensor = sensor;
		this.rate = rate;
	}
	
	
}
