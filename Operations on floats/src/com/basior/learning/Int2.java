package com.basior.learning;

import android.os.Handler;

public class Int2 extends AbstractCalculator {

	public Int2(Handler handler, int n, int loop) {
		super(handler, n, loop);
	}

	@Override
	public void calculate() {
		
		int temp_sum = 0;
		for (int i = 0; i < input.length; i++) {
			temp_sum += (input[i] * input[i] >> 10);
		}
		sum = temp_sum / 1048576.0f;
	}

}
