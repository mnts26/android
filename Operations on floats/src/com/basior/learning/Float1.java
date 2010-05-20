package com.basior.learning;

import android.os.Handler;

public class Float1 extends AbstractCalculator {

	public Float1(Handler handler, int n, int loop) {
		super(handler, n, loop);
	}

	@Override
	public void calculate() {
		
		for (int i = 0; i < input.length; i++) {
			
			float partialResult = input[i] / 32768.0f;
    		result[i] = partialResult * partialResult;
    		sum += result[i]; 
		}
	}

}
