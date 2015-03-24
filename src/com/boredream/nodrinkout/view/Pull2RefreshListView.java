package com.boredream.nodrinkout.view;

import android.content.Context;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class Pull2RefreshListView extends PullToRefreshListView {

	public Pull2RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init(context);
	}

	public Pull2RefreshListView(
			Context context,
			com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode,
			com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle style) {
		super(context, mode, style);
		
		init(context);
	}

	public Pull2RefreshListView(Context context,
			com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode) {
		super(context, mode);
		
		init(context);
	}

	public Pull2RefreshListView(Context context) {
		super(context);
		
		init(context);
	}
	

	private void init(Context context) {
		
	}

}
