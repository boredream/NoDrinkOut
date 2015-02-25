package com.boredream.nodrinkout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * ��ȹ̶����߶Ȱ���������Ӧ��ImageView
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
