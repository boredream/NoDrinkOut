package com.boredream.nodrinkout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * 宽度固定，高度按比例自适应的GridView
 */
public class WrapHeightGridView extends GridView {

	public WrapHeightGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public WrapHeightGridView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public WrapHeightGridView(Context context) {
		this(context, null);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
		
//        int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);
//        super.onMeasure(expandSpec, heightMeasureSpec);

//        ViewGroup.LayoutParams params = getLayoutParams();
//        params.height = getMeasuredHeight();
	}
}
