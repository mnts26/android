package com.basior.learning.CustomList;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ImageView.ScaleType;

class CustomListAdapter extends ListAdapterWrapper {

	private Context context;
	
	public CustomListAdapter(Context context, ListAdapter delegate) {
		super(delegate);
		
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null)
		{
			LinearLayout layout = new LinearLayout(context);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			ImageView image = new ImageView(context);
			ImageResourceProvider provider = (ImageResourceProvider) delegate.getItem(position);
			image.setImageResource(provider.getImageResourceId());
			image.setScaleType(ScaleType.CENTER_INSIDE);
			View guts = delegate.getView(position, null, parent);	

			image.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.FILL_PARENT));
		
			guts.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.FILL_PARENT));
			
			layout.addView(image);
			layout.addView(guts);
			
			RowWrapper row = new RowWrapper(layout);
			layout.setTag(row);
			convertView = layout;
		}
		else 
		{
			RowWrapper row = (RowWrapper) convertView.getTag();
			ImageResourceProvider provider = (ImageResourceProvider) delegate.getItem(position);
			row.getImage().setImageResource(provider.getImageResourceId());
			row.setGuts(delegate.getView(position, row.getGuts(), parent));
		}
		
		
		return convertView;
	}

	
		
}
