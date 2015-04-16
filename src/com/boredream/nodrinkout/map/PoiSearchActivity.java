package com.boredream.nodrinkout.map;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.datatype.BmobGeoPoint;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.navisdk.ui.routeguide.subview.E;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.listener.FindSimpleListener;
import com.boredream.nodrinkout.listener.SaveSimpleListener;

/**
 * 演示poi搜索功能
 */
public class PoiSearchActivity extends FragmentActivity implements
		OnGetPoiSearchResultListener, OnGetSuggestionResultListener {
	
	private PoiSearch mPoiSearch = null;
	private SuggestionSearch mSuggestionSearch = null;
	private BaiduMap mBaiduMap = null;
	/**
	 * 搜索关键字输入窗口
	 */
	private AutoCompleteTextView keyWorldsView = null;
	private ArrayAdapter<String> sugAdapter = null;
	// 当前页
	private int pageIndex = 0;
	// 当前上传的数据临时index,检测是否成功上传10条数据
	private int tempCompleteCount = 0;
	// 当前上传的数据index
	private int uploadCount = 0;
	// 当前搜索的数据index
	private int loadCount = 0;
	
	private PoiResult result;
	
	
	////////////////////////////////////////
	private List<CoffeeShop> allShops = new ArrayList<CoffeeShop>();
	// 当前页
	private int pageIndexDetail = 0;
	// 当前上传的数据临时index,检测是否成功上传10条数据
	private int tempCompleteCountDetail = 0;
	// 当前上传的数据index
	private int uploadCountDetail = 0;
	// 当前搜索的数据index
	private int loadCountDetail = 0;
	
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if(msg.what == 110) {
				goToNextPage(null);
			}
		}
		
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_poisearch);
		// 初始化搜索模块，注册搜索事件监听
		mPoiSearch = PoiSearch.newInstance();
		mPoiSearch.setOnGetPoiSearchResultListener(this);
		mSuggestionSearch = SuggestionSearch.newInstance();
		mSuggestionSearch.setOnGetSuggestionResultListener(this);
		keyWorldsView = (AutoCompleteTextView) findViewById(R.id.searchkey);
		sugAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line);
		keyWorldsView.setAdapter(sugAdapter);
		mBaiduMap = ((SupportMapFragment) (getSupportFragmentManager()
				.findFragmentById(R.id.map))).getBaiduMap();

		/**
		 * 当输入关键字变化时，动态更新建议列表
		 */
		keyWorldsView.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				if (cs.length() <= 0) {
					return;
				}
				String city = ((EditText) findViewById(R.id.city)).getText()
						.toString();
				/**
				 * 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
				 */
				mSuggestionSearch
						.requestSuggestion((new SuggestionSearchOption())
								.keyword(cs.toString()).city(city));
			}
		});

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mPoiSearch.destroy();
		mSuggestionSearch.destroy();
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	/**
	 * 开始按条件搜索列表结果
	 */
	public void searchButtonProcess(View v) {
		EditText editCity = (EditText) findViewById(R.id.city);
		EditText editSearchKey = (EditText) findViewById(R.id.searchkey);
		mPoiSearch.searchInCity((new PoiCitySearchOption())
				.city(editCity.getText().toString())
				.keyword(editSearchKey.getText().toString())
				.pageNum(pageIndex));
	}
	
	/**
	 * 开始按条件搜索详情结果
	 */
	public void searchDetailButtonProcess(View v) {
		BmobApi.queryShopsWhere(this, null, -1, 
				new FindSimpleListener<CoffeeShop>(this, null){

					@Override
					public void onSuccess(List<CoffeeShop> arg0) {
						super.onSuccess(arg0);
						
						allShops = arg0;
					}
		});
		
		for(int i=uploadCountDetail; i<uploadCountDetail+10; i++) {
			CoffeeShop coffeeShop = allShops.get(i);
			if(coffeeShop.isHasCaterDetails()) {
				mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
						.poiUid(coffeeShop.getUid()));
			}
			
		}
	}

	public void goToNextPage(View v) {
		pageIndex++;
		searchButtonProcess(null);
	}
	
	// 上传店铺信息
	public void upload(View v) {
		CoffeeShop shopBD;
		for(PoiInfo info : result.getAllPoi()) {
			shopBD = new CoffeeShop();
			shopBD.setAddress(info.address);
			shopBD.setCity(info.city);
			shopBD.setUid(info.uid);
			shopBD.setType(info.type.toString());
			shopBD.setLocation(new BmobGeoPoint(
					info.location.longitude, info.location.latitude));
			shopBD.setName(info.name);
			shopBD.setPhoneNum(info.phoneNum);
			shopBD.setPano(info.isPano);
			shopBD.setHasCaterDetails(info.hasCaterDetails);
			
			// customer
			shopBD.setFollowCount(0);
			shopBD.setInfoCount(0);
			shopBD.setImgUrl("");
			shopBD.save(this, new SaveSimpleListener(this, null){

				@Override
				public void onFailure(int arg0, String arg1) {
					super.onFailure(arg0, arg1);
					
					uploadCount ++;
					tempCompleteCount ++;
					if(isTempComplete()) {
						handler.sendEmptyMessageDelayed(110, 1000);
					}
					System.out.println("upload failure " + uploadCount);
				}

				@Override
				public void onSuccess() {
					super.onSuccess();
					
					uploadCount ++;
					tempCompleteCount ++;
					if(isTempComplete()) {
						handler.sendEmptyMessageDelayed(110, 1000);
					}
					System.out.println("upload success " + uploadCount);
				}
				
				private boolean isTempComplete() {
					if(tempCompleteCount == 10) {
						tempCompleteCount = 0;
						return true;
					}
					return false;
				}
				
			});
		}
	}
	
	public void uploadAllDetailInfo() {
		
	}

	public void onGetPoiResult(PoiResult result) {
		if (result == null
				|| result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
			Toast.makeText(PoiSearchActivity.this, "未找到结果", Toast.LENGTH_LONG)
			.show();
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			mBaiduMap.clear();
			PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
			mBaiduMap.setOnMarkerClickListener(overlay);
			overlay.setData(result);
			overlay.addToMap();
			overlay.zoomToSpan();
			
			this.result = result;
			loadCount += result.getAllPoi().size();
			System.out.println("获取数据" + result.getAllPoi().size() +", 加上后共" + loadCount);
			
			upload(null);
			
			return;
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

			// 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
			String strInfo = "在";
			for (CityInfo cityInfo : result.getSuggestCityList()) {
				strInfo += cityInfo.city;
				strInfo += ",";
			}
			strInfo += "找到结果";
			Toast.makeText(PoiSearchActivity.this, strInfo, Toast.LENGTH_LONG)
					.show();
		}
	}

	public void onGetPoiDetailResult(PoiDetailResult result) {
		if (result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(PoiSearchActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
					.show();
		} else {
			
		}
	}

	@Override
	public void onGetSuggestionResult(SuggestionResult res) {
		if (res == null || res.getAllSuggestions() == null) {
			return;
		}
		sugAdapter.clear();
		for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
			if (info.key != null)
				sugAdapter.add(info.key);
		}
		sugAdapter.notifyDataSetChanged();
	}

	private class MyPoiOverlay extends PoiOverlay {

		public MyPoiOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}

		@Override
		public boolean onPoiClick(int index) {
			super.onPoiClick(index);
			PoiInfo poi = getPoiResult().getAllPoi().get(index);
			// if (poi.hasCaterDetails) {
				mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
						.poiUid(poi.uid));
			// }
			return true;
		}
	}
}
