package com.basior.learning;

import java.util.Date;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public abstract class AbstractCalculator implements Runnable {

	int input[];
	float result[];
	final int loop;
	
	long duration;
	float sum = 0;
	
	Handler handler;
	
	public AbstractCalculator(Handler handler, int N, int loop) {
		super();
		this.loop = loop;
		this.handler = handler;
		
		input = new int[N];
		result = new float[N];
		Random r = new Random(0);
		
		for (int i = 0; i < input.length; i++) {
			input[i] = r.nextInt(32768+1); 
		}
	}
	
	
	public void run()
	{
		long start = new Date().getTime();
		for (int i = 0; i < loop; i++) {
			sum = 0;
			calculate();	
		}
		
		duration = new Date().getTime() - start; 
		sendMessage(duration);
	}


	private void sendMessage(long duration ) {
		Message msg = handler.obtainMessage();
		Bundle data = new Bundle();
		
		String algorithmName = this.getClass().getName();
		algorithmName = algorithmName.substring(algorithmName.lastIndexOf('.')+1);
		data.putString("Algorithm", algorithmName);
		data.putLong("Time", duration);
		data.putFloat("Sum", getSum());
		msg.setData(data);
		handler.sendMessage(msg);
	}
	
	
	public abstract void calculate();


	public long getDuration() {
		return duration;
	}


	public float getSum() {
		return sum;
	}
	
	
}
