package com.boredream.nodrinkout.listener;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;
import cn.bmob.v3.listener.FindListener;

public class FindSimpleListener<T> extends FindListener<T> {

	private Context context;
	private Dialog loadDialog;

	public FindSimpleListener(Context context, Dialog loadDialog) {
		this.context = context;
		this.loadDialog = loadDialog;
	}

	@Override
	public void onError(int arg0, String arg1) {
		if(loadDialog != null) {
			loadDialog.dismiss();
		}
		Toast.makeText(context, arg1, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSuccess(List<T> arg0) {
		if(loadDialog != null) {
			loadDialog.dismiss();
		}
	}

}
