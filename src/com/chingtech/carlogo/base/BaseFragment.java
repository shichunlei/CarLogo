package com.chingtech.carlogo.base;

import java.io.Serializable;

import com.chingtech.carlogo.R;
import com.chingtech.carlogo.widget.CircularProgressDialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * 
 * 页面说明：基类
 * 
 * @author 师春雷
 * @date 2015-8-7
 * @update 2015-10-7
 * @version 1.1
 * 
 */
public class BaseFragment extends Fragment {

	/** 上下文 */
	public static Context context;

	public CircularProgressDialog loading;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		context = getActivity();
		loading = CircularProgressDialog.show(context);
	}

	/**
	 * 接收前一个页面传递的String值
	 * 
	 * @param key
	 * @return
	 */
	protected String getStringExtra(String key) {
		Intent receive = ((Activity) context).getIntent();
		String flag;

		flag = receive.getStringExtra(key);

		return flag;
	}

	/**
	 * 接收前一个页面传递的Integer值
	 * 
	 * @param key
	 * @return
	 */
	protected Integer getIntExtra(String key) {
		Intent receive = ((Activity) context).getIntent();
		int flag;

		flag = receive.getIntExtra(key, 0);

		return flag;
	}

	/**
	 * 接收前一个页面传递的Boolean值
	 * 
	 * @param key
	 * @return
	 */
	protected boolean getBooleanExtra(String key) {
		Intent receive = ((Activity) context).getIntent();
		boolean flag;

		flag = receive.getBooleanExtra(key, false);

		return flag;
	}

	/**
	 * 接收前一个页面传递的Float值
	 * 
	 * @param key
	 * @return
	 */
	protected Float getFloatExtra(String key) {
		Intent receive = ((Activity) context).getIntent();
		Float flag;

		flag = receive.getFloatExtra(key, 0f);

		return flag;
	}

	/**
	 * 接收前一个页面传递的Long值
	 * 
	 * @param key
	 * @return
	 */
	protected Long getLongExtra(String key) {
		Intent receive = ((Activity) context).getIntent();
		Long flag;

		flag = receive.getLongExtra(key, 0L);

		return flag;
	}

	/**
	 * 接收前一个页面传递的Double值
	 * 
	 * @param key
	 * @return
	 */
	protected Double getDoubleExtra(String key) {
		Intent receive = ((Activity) context).getIntent();
		Double flag;

		flag = receive.getDoubleExtra(key, 0.0);

		return flag;
	}

	/**
	 * 通过类名启动Activity
	 * 
	 * @param pClass
	 * @param isfinish
	 */
	protected void openActivity(Class<?> pClass) {
		showActivity(pClass, null, null, null, false, 0);
	}

	protected void openActivity(Class<?> pClass, boolean isfinish) {
		showActivity(pClass, null, null, null, isfinish, 0);
	}

	protected void openActivity(Class<?> pClass, int i) {
		showActivity(pClass, null, null, null, false, i);
	}

	protected void openActivity(Class<?> pClass, String key, Serializable value, int i) {
		showActivity(pClass, null, key, value, false, i);
	}

	/**
	 * 通过类名启动Activity
	 * 
	 * @param pClass
	 * @param key
	 * @param value
	 * @param isfinish
	 */
	protected void openActivity(Class<?> pClass, String key, Serializable value) {
		showActivity(pClass, null, key, value, false, 0);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 * 
	 * @param pClass
	 * @param pBundle
	 * @param isfinish
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle, boolean isfinish) {
		showActivity(pClass, pBundle, null, null, isfinish, 0);
	}

	protected void openActivity(Class<?> pClass, String key, Serializable value, boolean isfinish) {
		showActivity(pClass, null, key, value, isfinish, 0);
	}

	protected void showActivity(Class<?> pClass, Bundle bundle, String key, Serializable value,
			boolean isfinish, int i) {

		Intent intent = new Intent(context, pClass);

		if (null != key) {
			intent.putExtra(key, value);
		}

		if (null != bundle) {
			intent.putExtras(bundle);
		}

		if (i == 0) {
			startActivity(intent);
		} else {
			startActivityForResult(intent, i);
		}

		((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		if (isfinish) {
			((Activity) context).finish();
		}
	}

	/**
	 * Toast提示
	 * 
	 * @param text
	 */
	public void showToast(String text) {
		Toast.makeText(context, text, 1).show();
	}

	/**
	 * Toast提示
	 * 
	 * @param resid
	 */
	public void showToast(int resid) {
		Toast.makeText(context, getString(resid), 1).show();
	}

}
