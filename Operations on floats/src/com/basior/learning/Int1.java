package com.basior.learning;

import android.os.Handler;

public class Int1 extends AbstractCalculator {

	public Int1(Handler handler, int n, int loop) {
		super(handler, n, loop);
	}

	@Override
	public void calculate() {
		for (int i = 0; i < input.length; i++) {
			
    		result[i] =  ( input[i] * input[i] >> 15) / 32768.0f;
    		sum += result[i];
		}
	}

}
