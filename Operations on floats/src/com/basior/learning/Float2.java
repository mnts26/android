package com.basior.learning;

import android.os.Handler;

public class Float2 extends AbstractCalculator {

	public Float2(Handler handler, int n, int loop) {
		super(handler, n, loop);
	}

	@Override
	public void calculate() {
		for (int i = 0; i < input.length; i++) {
			
    		result[i] = input[i] * input[i] / (1073741824f);
    		sum += result[i];
		}
	}

}
