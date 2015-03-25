package com.boredream.nodrinkout.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.boredream.nodrinkout.R;

public class DialogUtils {

	public static Dialog createLoadingDialog(Context context) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		return progressDialog;
	}

	public static Dialog createCommentDialog(Context context) {
		Dialog dialog = new Dialog(context, R.style.DialogOtherTran);
		dialog.setContentView(R.layout.dialog_add_comment);
		
		Window window = dialog.getWindow();
		WindowManager.LayoutParams p = window.getAttributes();
		p.width = DisplayUtils.getScreenWidthPixels((Activity)context);
		window.setAttributes(p);
		window.setGravity(Gravity.BOTTOM);
		dialog.setCanceledOnTouchOutside(true);
		
		return dialog;
	}
}
