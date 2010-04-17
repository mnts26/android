package com.basior.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class MyButton extends Button {

	public MyButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public MyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	public String getBuzz()
	{
		return "Buzz";
	}
	
	public void setBuzz(String s)
	{
		
	}

}
