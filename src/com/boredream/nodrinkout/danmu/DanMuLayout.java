package com.boredream.nodrinkout.danmu;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

public class DanMuLayout extends FrameLayout {

	private int mRight;
	private int mleft;
	private long mDefDurTime = 5000;

	private long startDmTime;
	private boolean isShowing;
	private ShowDanMuThread dmThread;
	

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			View child = getChildAt(msg.what);

			TranslateAnimation animation = new TranslateAnimation(0, 
					mleft - mRight - child.getMeasuredWidth(), 0, 0);
			animation.setDuration(mDefDurTime);
			child.startAnimation(animation);

			invalidate();

			Log.i("DDD", child.getTag().toString());
		}

	};

	public DanMuLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public DanMuLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DanMuLayout(Context context) {
		super(context);
	}

	public void setDanMu(List<DanMu> dms) {
		for(int i=0; i<dms.size(); i++) {
			DanMu dm = dms.get(i);
			TextView tv = new TextView(getContext());
			tv.setText(dm.content);
			tv.setTag(dm);
			addView(tv);
		}
	}

	public void startShowDanMu() {
		if (dmThread == null) {
			dmThread = new ShowDanMuThread();
		}
		startDmTime = System.currentTimeMillis();
		isShowing = true;
		dmThread.start();
	}
	
	public void stopShowDanMu() {
		isShowing = false;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {

		mleft = left;
		mRight = right;

		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.layout(right, 
					20 * (i + 1), 
					right + child.getMeasuredWidth(),
					20 * (i + 1) + child.getMeasuredHeight());
		}
	}

	private class ShowDanMuThread extends Thread {
		@Override
		public void run() {
			super.run();
			while (isShowing) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				showDanMuInTime();
			}
		}
	}
	
	private void showDanMuInTime() {
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			DanMu dm = (DanMu) child.getTag();

			Log.i("DDD", "def time = " + (System.currentTimeMillis() - startDmTime));
			if (System.currentTimeMillis() - startDmTime >= dm.time
					&& !dm.hasShow) {
				handler.sendEmptyMessage(i);
				dm.hasShow = true;
			}
		}
	}

}
