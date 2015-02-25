package com.boredream.nodrinkout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.boredream.nodrinkout.R;

public class DrawableTextView extends TextView {

	public DrawableTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.DrawableTextView);

		int widthPx = ta.getDimensionPixelSize(
				R.styleable.DrawableTextView_drawableWidth, -1);
		int heightPx = ta.getDimensionPixelSize(
				R.styleable.DrawableTextView_drawableHeight, -1);

		Drawable[] drawables = getCompoundDrawables();
		Drawable drawable = null;
		for (int i = 0; i < drawables.length; i++) {
			if (drawables[i] != null) {
				drawable = drawables[i];
				break;
			}
		}

		if (drawable != null && widthPx != -1 && heightPx != -1) {
			drawable.setBounds(0, 0, widthPx, heightPx);
		}

		// 将图片放回到TextView中
		setCompoundDrawables(drawables[0], drawables[1], drawables[2],
				drawables[3]);

		ta.recycle();
	}

	public DrawableTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DrawableTextView(Context context) {
		this(context, null);
	}

}
