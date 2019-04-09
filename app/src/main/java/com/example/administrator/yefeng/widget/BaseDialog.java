package com.example.administrator.yefeng.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * 基础dialog
 * 
 * 完成一项自定义dialog 的公共方法
 */
public class BaseDialog extends Dialog implements
		View.OnClickListener {

	protected BaseDialog(Context context, boolean cancelable,
                         OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	public BaseDialog(Context context2, int dialogLogin) {
		super(context2, dialogLogin);
	}

	public BaseDialog(Context context2) {
		super(context2);
	}
 

	protected Context context;

	/**
	 * 提示
	 * 
	 * @param
	 */
	protected void showToast(String Msg) {
		Toast.makeText(context, Msg,Toast.LENGTH_LONG).show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
 

}

