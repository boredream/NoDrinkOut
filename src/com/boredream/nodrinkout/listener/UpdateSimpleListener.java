package com.boredream.nodrinkout.listener;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;
import cn.bmob.v3.listener.UpdateListener;

public class UpdateSimpleListener extends UpdateListener {

	private Context context;
	private Dialog loadDialog;

	public UpdateSimpleListener(Context context, Dialog loadDialog) {
		this.context = context;
		this.loadDialog = loadDialog;
	}

	@Override
	public void onFailure(int arg0, String arg1) {
		if(loadDialog != null) {
			loadDialog.dismiss();
		}
		Toast.makeText(context, arg1, Toast.LENGTH_SHORT).show();
	
	}

	@Override
	public void onSuccess() {
		if(loadDialog != null) {
			loadDialog.dismiss();
		}
	}

}
