package com.boredream.nodrinkout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 宽度固定，高度按比例自适应的ImageView
 */
public class WrapHeightImageView extends ImageView {

	public WrapHeightImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public WrapHeightImageView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public WrapHeightImageView(Context context) {
		this(context, null);
	}
}
