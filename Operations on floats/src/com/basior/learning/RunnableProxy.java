package com.basior.learning;

public class RunnableProxy implements Runnable {

	Runnable runner;
	
	
	
	public RunnableProxy(Runnable runner) {
		super();
		this.runner = runner;
	}



	@Override
	public void run() {
		new Thread(runner).start();
	}

}
