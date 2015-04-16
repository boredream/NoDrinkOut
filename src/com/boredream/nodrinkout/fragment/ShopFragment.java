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
	private Pull2RefreshListView plv_shop_nearest;
	private View footViewNearest;
	private RadioGroup rg_shop;
	
	/**
	 * 1-最热 2-最近
	 */
	private int curType = 1;
	
	////////////////////////////////////////////////

	private ShopAdapter mostFollowShopAdapter;
	private List<CoffeeShop> mostFollowShops = new ArrayList<CoffeeShop>();
	private int mostFollowCurPage = 1;
	private boolean isLoadingMostFollow = false;
	
	////////////////////////////////////////////////
	
	private ShopAdapter nearestShopAdapter;
	private List<CoffeeShop> nearestShops = new ArrayList<CoffeeShop>();
	private int nearestCurPage = 1;
	private boolean isLoadingNearest = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(getActivity(), R.layout.frag_shop, null);

		initView();
		setData();
		
		showMostFollow();
		
		return view;
	}
	
	private void initView() {
		new TitleBuilder(view).setTitleText("咖店").build();
		rg_shop = (RadioGroup) view.findViewById(R.id.rg_shop);
		rg_shop.setOnCheckedChangeListener(this);
		
		plv_shop_most_follow = (Pull2RefreshListView) view.findViewById(R.id.plv_shop_most_follow);
		plv_shop_nearest = (Pull2RefreshListView) view.findViewById(R.id.plv_shop_nearest);
		
		footViewMostFollow = View.inflate(activity, R.layout.footview_loading, null);
		footViewNearest = View.inflate(activity, R.layout.footview_loading, null);
	}
	
	private void setData() {
		mostFollowShopAdapter = new ShopAdapter(activity, mostFollowShops);
		plv_shop_most_follow.setAdapter(mostFollowShopAdapter);
		plv_shop_most_follow.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				loadData(curType, 1);
			}
		});
		plv_shop_most_follow.setOnItemClickListener(new SimpleOnItemClickListener(activity));
		plv_shop_most_follow.setOnLastItemVisibleListener(
				new OnLastItemVisibleListener() {

					@Override
					public void onLastItemVisible() {
						if (!isLoadingMostFollow) {
							loadData(curType, mostFollowCurPage + 1);
						}
					}
				});

		nearestShopAdapter = new ShopAdapter(activity, nearestShops, 2);
		plv_shop_nearest.setAdapter(nearestShopAdapter);
		plv_shop_nearest.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				loadData(curType, 1);
			}
		});
		plv_shop_nearest.setOnItemClickListener(new SimpleOnItemClickListener(activity));
		plv_shop_nearest.setOnLastItemVisibleListener(
				new OnLastItemVisibleListener() {

					@Override
					public void onLastItemVisible() {
						isLoadingNearest = true;

						if (!isLoadingNearest) {
							loadData(curType, nearestCurPage + 1);
						}
					}
				});

	}

	private void loadData(final int type, final int pageIndex) {
		if (type == 1) {
			isLoadingMostFollow = true;
			BmobApi.queryShopsWhere(activity, "followCount", pageIndex,
					new FindSimpleListener<CoffeeShop>(activity, loadDialog) {

						@Override
						public void onSuccess(List<CoffeeShop> arg0) {
							super.onSuccess(arg0);

							// 如果成功加载数据了,更新当前页索引,否则不变
							mostFollowCurPage = pageIndex;
							if(pageIndex == 1) {
								mostFollowShops.clear();
							}

							mostFollowShops.addAll(arg0);
							mostFollowShopAdapter.notifyDataSetChanged();
							plv_shop_most_follow.onRefreshComplete();
							
							if(arg0.size() == CommonConstants.COUNT_PER_PAGE) {
								addFootView(plv_shop_most_follow, footViewMostFollow);
							} else {
								removeFootView(plv_shop_most_follow, footViewMostFollow);
							}
						}

						@Override
						public void onFinish() {
							super.onFinish();

							isLoadingMostFollow = false;
						}

					});
		} else if(type == 2) {
			isLoadingNearest = true;
			
			BmobGeoPoint geo = new BmobGeoPoint(BaseApplication.curLocation.longitude, 
					BaseApplication.curLocation.latitude);
			
			BmobApi.queryShopsWhereNear(activity, geo, pageIndex,
					new FindSimpleListener<CoffeeShop>(activity, loadDialog) {
				
				@Override
				public void onSuccess(List<CoffeeShop> arg0) {
					super.onSuccess(arg0);
					
					// 如果成功加载数据了,更新当前页索引,否则不变
					nearestCurPage = pageIndex;
					if(pageIndex == 1) {
						nearestShops.clear();
					}
					
					nearestShops.addAll(arg0);
					nearestShopAdapter.notifyDataSetChanged();
					plv_shop_nearest.onRefreshComplete();
					
					if(arg0.size() == CommonConstants.COUNT_PER_PAGE) {
						addFootView(plv_shop_nearest, footViewNearest);
					} else {
						removeFootView(plv_shop_nearest, footViewNearest);
					}
				}
				
				@Override
				public void onFinish() {
					super.onFinish();
					
					isLoadingNearest = false;
				}
				
			});
			
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
			showMostFollow();
			break;
		case R.id.rb_nearest:
			showNearest();
			break;

		default:
			break;
		}
	}

	private void showMostFollow() {
		curType = 1;
		
		plv_shop_most_follow.setVisibility(View.VISIBLE);
		plv_shop_nearest.setVisibility(View.GONE);
		
		if(mostFollowShops.size() == 0) {
			loadData(curType, 1);
		}
	}
	
	private void showNearest() {
		curType = 2;
		
		plv_shop_most_follow.setVisibility(View.GONE);
		plv_shop_nearest.setVisibility(View.VISIBLE);
		
		if(nearestShops.size() == 0) {
			loadData(curType, 1);
		}
	}
}
