package com.chingtech.carlogo.widget;

import com.chingtech.carlogo.R;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * @Title: AlertDialog
 * @Description: 自定义AlertDialog
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月4日下午3:40:16
 */
public class AlertDialog {

	private Context context;
	private Dialog dialog;

	/** 标题 */
	private TextView txt_title;
	/** 信息 */
	private TextView txt_message;
	/** 确定按钮 */
	private Button btn_neg;
	/** 取消按钮 */
	private Button btn_pos;

	private boolean canceledOnTouchOutside = false;

	private boolean cancelable = true;

	public AlertDialog(Context context) {
		this.context = context;
	}

	public AlertDialog builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(R.layout.view_alertdialog, null);

		txt_title = (TextView) view.findViewById(R.id.dialog_title);
		txt_message = (TextView) view.findViewById(R.id.dialog_message);
		btn_neg = (Button) view.findViewById(R.id.dialog_cancel);
		btn_pos = (Button) view.findViewById(R.id.dialog_sure);

		// 定义Dialog布局和参数
		dialog = new Dialog(context, R.style.AlertDialogStyle);
		dialog.setContentView(view);

		return this;
	}

	public AlertDialog setTitle(String title) {
		if (!TextUtils.isEmpty(title)) {
			txt_title.setText(title);
		} else {
			txt_title.setText("温馨提示");
		}
		return this;
	}

	public AlertDialog setMsg(String msg) {
		if (!TextUtils.isEmpty(msg)) {
			txt_message.setText(msg);
		} else {
			txt_message.setText("内容");
		}
		return this;
	}

	public AlertDialog setPositiveButton(String text, final OnClickListener listener) {
		if (!TextUtils.isEmpty(text)) {
			btn_pos.setText(text);
		} else {
			btn_pos.setText("确定");
		}
		btn_pos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != listener) {
					listener.onClick(v);
				}
				dialog.dismiss();
			}
		});
		return this;
	}

	public AlertDialog setNegativeButton(String text, final OnClickListener listener) {
		if (!TextUtils.isEmpty(text)) {
			btn_neg.setText(text);
		} else {
			btn_neg.setText("取消");
		}

		btn_neg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != listener) {
					listener.onClick(v);
				}
				dialog.dismiss();
			}
		});

		return this;
	}

	public boolean isCanceledOnTouchOutside() {
		return canceledOnTouchOutside;
	}

	public AlertDialog setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
		this.canceledOnTouchOutside = canceledOnTouchOutside;
		return this;
	}

	public boolean isCancelable() {
		return cancelable;
	}

	public AlertDialog setCancelable(boolean cancelable) {
		this.cancelable = cancelable;
		return this;
	}

	public void show() {
		// 调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
		dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
		// 调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
		dialog.setCancelable(cancelable);
		dialog.show();
	}

}
