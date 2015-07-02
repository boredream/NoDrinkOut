package com.boredream.nodrinkout.listener;

import android.content.Context;
import android.content.Intent;

import com.boredream.nodrinkout.activity.InfoDetailActivity;
import com.boredream.nodrinkout.activity.ShopDetailActivity;
import com.boredream.nodrinkout.activity.UserActivity;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.entity.User;

public class OnAdapterMultiClickListener {

	public static final int TYPE_SCAN_IMAGES = 1;
	public static final int TYPE_INFO_DETAIL = 2;
	public static final int TYPE_USER_DETAIL = 3;
	public static final int TYPE_SHOP_DETAIL = 4;

	private Context context;

	public OnAdapterMultiClickListener(Context context) {
		this.context = context;
	}

	public void onItemClick(int type, Object obj) {
		switch (type) {
		case TYPE_SCAN_IMAGES:

			break;
		case TYPE_INFO_DETAIL:
			CoffeeInfo info = (CoffeeInfo) obj;
			Intent intent = new Intent(context, InfoDetailActivity.class);
			intent.putExtra("info", info);
			context.startActivity(intent);
			break;
		case TYPE_USER_DETAIL:
			User user = (User) obj;
			Intent intent2 = new Intent(context, UserActivity.class);
			intent2.putExtra("user", user);
			context.startActivity(intent2);
			break;
		case TYPE_SHOP_DETAIL:
			CoffeeShop shop = (CoffeeShop) obj;
			Intent intent3 = new Intent(context, ShopDetailActivity.class);
			intent3.putExtra("shop", shop);
			context.startActivity(intent3);
			break;

		default:
			break;
		}
	}
}
