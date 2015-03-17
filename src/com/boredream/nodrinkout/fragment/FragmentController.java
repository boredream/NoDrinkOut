package com.boredream.nodrinkout.fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class FragmentController {

	private int containerId;
	private android.support.v4.app.FragmentManager fm;
	
	private HomeFragment homeFragment;
	private static final String TAG_HOME = "home";
	private ShopFragment shopFragment;
	private static final String TAG_SHOP = "shop";
	private SearchFragment searchFragment;
	private static final String TAG_SEARCH = "search";
	private UserFragment userFragment;
	private static final String TAG_USER = "user";
	
	public FragmentController(FragmentActivity activity, int containerId) {
		this.containerId = containerId;
		fm = activity.getSupportFragmentManager();
	}
	
	public void showHomeFragment() {
		hideFragments();
		
		if(homeFragment == null) {
			homeFragment = new HomeFragment();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(containerId, homeFragment, TAG_HOME);
			ft.commit();
		} else {
			FragmentTransaction ft = fm.beginTransaction();
			ft.show(homeFragment);
			ft.commit();
		}
	}
	
	public void showShopFragment() {
		hideFragments();
		
		if(shopFragment == null) {
			shopFragment = new ShopFragment();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(containerId, shopFragment, TAG_SHOP);
			ft.commit();
		} else {
			FragmentTransaction ft = fm.beginTransaction();
			ft.show(shopFragment);
			ft.commit();
		}
	}
	
	public void showSearchFragment() {
		hideFragments();
		
		if(searchFragment == null) {
			searchFragment = new SearchFragment();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(containerId, searchFragment, TAG_SEARCH);
			ft.commit();
		} else {
			FragmentTransaction ft = fm.beginTransaction();
			ft.show(searchFragment);
			ft.commit();
		}
	}
	
	public void showUserFragment() {
		hideFragments();
		
		if(userFragment == null) {
			userFragment = new UserFragment();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(containerId, userFragment, TAG_USER);
			ft.commit();
		} else {
			FragmentTransaction ft = fm.beginTransaction();
			ft.show(userFragment);
			ft.commit();
		}
	}
	
	public void hideFragments() {
		FragmentTransaction ft = fm.beginTransaction();
		if(homeFragment != null) {
			ft.hide(homeFragment);
		}
		if(shopFragment != null) {
			ft.hide(shopFragment);
		}
		if(searchFragment != null) {
			ft.hide(searchFragment);
		}
		if(userFragment != null) {
			ft.hide(userFragment);
		}
		ft.commit();
	}

}