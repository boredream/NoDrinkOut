package com.boredream.nodrinkout.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.boredream.nodrinkout.activity.InfoDetailActivity;
import com.boredream.nodrinkout.activity.ShopDetailActivity;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.CoffeeShop;

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
		} else if(obj instanceof CoffeeShop) {
			CoffeeShop shop = (CoffeeShop) obj;
			
			Intent intent = new Intent(context, ShopDetailActivity.class);
			intent.putExtra("shop", shop);
			context.startActivity(intent);
		}
		
		
	}

}
