package com.boredream.nodrinkout.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtils {

	public static Dialog createLoadingDialog(Context context) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		return progressDialog;
	}
	
}
