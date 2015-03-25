package com.boredream.nodrinkout.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.boredream.nodrinkout.activity.InfoDetailActivity;
import com.boredream.nodrinkout.entity.CoffeeInfo;

public class SimpleOnItemListener implements OnItemClickListener {
	
	private Context context;

	public SimpleOnItemListener(Context context) {
		this.context = context;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Object obj = parent.getAdapter().getItem(position);
		if(obj instanceof CoffeeInfo) {
			CoffeeInfo info = (CoffeeInfo) obj;
			
			Intent intent = new Intent(context, InfoDetailActivity.class);
			intent.putExtra("info", info);
			context.startActivity(intent);
		}
		
		
	}

}
