package com.basior.learning;

import android.view.ViewGroup;
import android.widget.TextView;

public class ViewWrapper {
	ViewGroup parent;
	TextView code;
	TextView country;
	public ViewWrapper(ViewGroup parent) {
		super();
		this.parent = parent;
	}

	public TextView getCode()
	{
		if (code == null)
		{
			code = (TextView)parent.findViewById(R.id.TextViewCode);
		}
		
		return code;
	}
	
	public TextView getCountry()
	{
		if (country == null)
		{
			country = (TextView)parent.findViewById(R.id.TextViewCountry);
		}
		
		return country;
	}
	
}
