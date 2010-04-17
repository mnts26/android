package com.basior.learning.CustomList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

class RowWrapper {

	private ImageView image;
	private View guts;
	private ViewGroup parent;
	
	
	public RowWrapper(ViewGroup parent) {
		super();
		this.parent = parent;
	}


	public ImageView getImage() {
		if (image == null)
		{
			image = (ImageView) parent.getChildAt(0);
		}
		return image;
	}


	public void setImage(ImageView image) {
		this.image = image;
	}


	public View getGuts() {
		if (guts == null)
		{
			guts = parent.getChildAt(1);
		}
		return guts;
	}


	public void setGuts(View guts) {
		this.guts = guts;
	}
	
	
	
	
}
