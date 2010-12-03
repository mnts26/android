package com.pogoda;

import java.util.Observable;
import java.util.Observer;

import org.junit.Test;


public class DownloadAreasTest {

	
	@Test 
	public void doInBackground () {
		DownloadAreas testee = new DownloadAreas(new Observer() {
			
			@Override
			public void update(Observable result, Object arg1) {
				Areas areas = (Areas) result;
				System.out.println(areas.getXml());
				
			}
		});
	}
}
