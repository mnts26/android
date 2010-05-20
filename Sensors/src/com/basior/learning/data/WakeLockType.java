package com.basior.learning.data;

import android.hardware.SensorManager;
import android.os.PowerManager;

public enum WakeLockType {

	FULL( "Full", PowerManager.FULL_WAKE_LOCK),
	BRIGHT( "Bright", PowerManager.SCREEN_BRIGHT_WAKE_LOCK),
	DIM( "Dim", PowerManager.SCREEN_DIM_WAKE_LOCK),
	PARTIAL( "Partial", PowerManager.PARTIAL_WAKE_LOCK);
	
	
	
	private WakeLockType(String description, int value) {
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