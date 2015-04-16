package com.boredream.nodrinkout.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import cn.bmob.v3.datatype.BmobGeoPoint;

import com.boredream.nodrinkout.BaseApplication;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.ShopAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.constants.CommonConstants;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.listener.FindSimpleListener;
import com.boredream.nodrinkout.listener.SimpleOnItemClickListener;
import com.boredream.nodrinkout.utils.TitleBuilder;
import com.boredream.nodrinkout.view.Pull2RefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ShopFragment extends BaseFragment implements OnCheckedChangeListener {
	
	private View view;
	private Pull2RefreshListView plv_shop_most_follow;
	private View footViewMostFollow;
//	private Pull2RefreshListView plv_shop_nearest;
//	private View footViewNearest;
	private RadioGroup rg_shop;
	
	/**
	 * 1-最热 2-最近
	 */
	private int curType = 1;
	private boolean isLoadingNextPage = false;
	private List<CoffeeShop> showShops = new ArrayList<CoffeeShop>();
	
	////////////////////////////////////////////////

	private ShopAdapter mostFollowShopAdapter;
	private List<CoffeeShop> mostFollowShops = new ArrayList<CoffeeShop>();
	private int mostFollowCurPage = 1;
	
	////////////////////////////////////////////////
	
//	private ShopAdapter nearestShopAdapter;
	private List<CoffeeShop> nearestShops = new ArrayList<CoffeeShop>();
	private int nearestCurPage = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(getActivity(), R.layout.frag_shop, null);

		initView();
		setData();
		loadData(1);
		
		return view;
	}
	
	private void initView() {
		new TitleBuilder(view).setTitleText("咖店").build();
		rg_shop = (RadioGroup) view.findViewById(R.id.rg_shop);
		rg_shop.setOnCheckedChangeListener(this);
		
		plv_shop_most_follow = (Pull2RefreshListView) view.findViewById(R.id.plv_shop_most_follow);
//		plv_shop_nearest = (Pull2RefreshListView) view.findViewById(R.id.plv_shop_nearest);
		
		footViewMostFollow = View.inflate(activity, R.layout.footview_loading, null);
//		footViewNearest = View.inflate(activity, R.layout.footview_loading, null);
	}
	
	private void setData() {
		mostFollowShopAdapter = new ShopAdapter(activity, showShops);
		plv_shop_most_follow.setAdapter(mostFollowShopAdapter);
		plv_shop_most_follow.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				if(curType == 1) {
					mostFollowShops.clear();
				} else {
					nearestShops.clear();
				}
				loadData(1);
			}
		});
		plv_shop_most_follow.setOnItemClickListener(new SimpleOnItemClickListener(activity));
		plv_shop_most_follow.setOnLastItemVisibleListener(
				new OnLastItemVisibleListener() {

					@Override
					public void onLastItemVisible() {
						if (!isLoadingNextPage) {
							loadData(curType == 1?(mostFollowCurPage + 1):(nearestCurPage + 1));
						}
					}
				});

//		nearestShopAdapter = new ShopAdapter(activity, nearestShops);
//		plv_shop_nearest.setAdapter(nearestShopAdapter);
//		plv_shop_nearest.setOnRefreshListener(new OnRefreshListener<ListView>() {
//
//			@Override
//			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//				nearestShops.clear();
//				loadData(1);
//			}
//		});
//		plv_shop_nearest.setOnItemClickListener(new SimpleOnItemClickListener(activity));
//		plv_shop_nearest.setOnLastItemVisibleListener(
//				new OnLastItemVisibleListener() {
//
//					@Override
//					public void onLastItemVisible() {
//						isLoadingNextPage = true;
//
//						if (!isLoadingNextPage) {
//							loadData(nearestCurPage + 1);
//						}
//					}
//				});

	}

	private void loadData(final int pageIndex) {
		isLoadingNextPage = true;
		if (curType == 1) {
			BmobApi.queryShopsWhere(activity, "followCount", pageIndex,
					new FindSimpleListener<CoffeeShop>(activity, loadDialog) {

						@Override
						public void onSuccess(List<CoffeeShop> arg0) {
							super.onSuccess(arg0);

							showListData(arg0, pageIndex);
						}

						@Override
						public void onFinish() {
							super.onFinish();

							isLoadingNextPage = false;
						}

					});
		} else if(curType == 2) {
			BmobGeoPoint geo = new BmobGeoPoint(BaseApplication.curLocation.longitude, 
					BaseApplication.curLocation.latitude);
			
			BmobApi.queryShopsWhereNear(activity, geo, pageIndex,
					new FindSimpleListener<CoffeeShop>(activity, loadDialog) {
				
				@Override
				public void onSuccess(List<CoffeeShop> arg0) {
					super.onSuccess(arg0);
					
					showListData(arg0, pageIndex);
				}
				
				@Override
				public void onFinish() {
					super.onFinish();
					
					isLoadingNextPage = false;
				}
				
			});
			
		}
	}
	
	private void showListData(List<CoffeeShop> shops, int pageIndex) {
		if(curType == 1) {
			// 如果成功加载数据了,更新当前页索引,否则不变
			mostFollowCurPage = pageIndex;
			mostFollowShops.addAll(shops);
			mostFollowShopAdapter.setDatas(mostFollowShops);
		} else {
			// 如果成功加载数据了,更新当前页索引,否则不变
			nearestCurPage = pageIndex;
			nearestShops.addAll(shops);
			mostFollowShopAdapter.setDatas(nearestShops);
		}

		mostFollowShopAdapter.notifyDataSetChanged();
		plv_shop_most_follow.onRefreshComplete();
		
		if(shops.size() == CommonConstants.COUNT_PER_PAGE) {
			addFootView(plv_shop_most_follow, footViewMostFollow);
		} else {
			removeFootView(plv_shop_most_follow, footViewMostFollow);
		}
	}

	private void addFootView(PullToRefreshListView plv, View footView) {
		ListView lv = plv.getRefreshableView();
		if(lv.getFooterViewsCount() == 1) {
			lv.addFooterView(footView);
		}
	}
	
	private void removeFootView(PullToRefreshListView plv, View footView) {
		ListView lv = plv.getRefreshableView();
		if(lv.getFooterViewsCount() > 1) {
			lv.removeFooterView(footView);
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb_most_follow:
			curType = 1;
			
//			plv_shop_most_follow.setVisibility(View.VISIBLE);
//			plv_shop_nearest.setVisibility(View.GONE);
			
			if(mostFollowShops.size() == 0) {
				loadData(1);
			}
			
			break;
		case R.id.rb_nearest:
			curType = 2;
			
//			plv_shop_most_follow.setVisibility(View.GONE);
//			plv_shop_nearest.setVisibility(View.VISIBLE);
			
			if(nearestShops.size() == 0) {
				loadData(1);
			}
			break;

		default:
			break;
		}
	}
}
