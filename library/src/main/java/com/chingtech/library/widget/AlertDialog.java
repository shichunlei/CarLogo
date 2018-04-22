package com.chingtech.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chingtech.library.R;
import com.chingtech.library.utils.StringUtils;


/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * Health-Old
 * Package com.chingtech.library.widget
 * Description:
 * Created by 师春雷
 * Created at 17/9/25 下午6:15
 */
public class AlertDialog {

    private Context context;
    private Dialog  dialog;


    RoundLinearLayout lLayout_bg;
    /** 标头 */
    private TextView txt_title;
    /** 提示信息 */
    private TextView txt_msg;
    LinearLayout layoutBottom;
    /** 确定按钮 */
    private TextView tv_neg;
    /** 取消按钮 */
    private TextView tv_pos;
    private View     vLine;

    private Display display;

    private boolean showTitle  = false;
    private boolean showMsg    = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public AlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    @SuppressWarnings("deprecation")
    public AlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.layout_normal_dialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = view.findViewById(R.id.lLayout_bg);
        txt_title = view.findViewById(R.id.tv_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = view.findViewById(R.id.tv_message);
        txt_msg.setVisibility(View.GONE);
        tv_neg = view.findViewById(R.id.tv_cancel);
        tv_neg.setVisibility(View.GONE);
        tv_pos = view.findViewById(R.id.tv_ok);
        tv_pos.setVisibility(View.GONE);
        layoutBottom = view.findViewById(R.id.layout_bottom);
        layoutBottom.setVisibility(View.GONE);
        vLine = view.findViewById(R.id.v_line);
        vLine.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85),
                                                                LinearLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public AlertDialog setTitle(int titleId) {
        setTitle(titleId, null, -1, -1);
        return this;
    }

    public AlertDialog setTitle(String title) {
        setTitle(-1, title, -1, -1);
        return this;
    }

    public AlertDialog setTitle(int titleId, int gravity) {
        setTitle(titleId, null, gravity, -1);
        return this;
    }

    public AlertDialog setTitle(String title, int gravity) {
        setTitle(-1, title, gravity, -1);
        return this;
    }

    public AlertDialog setTitle(String title, int gravity, int textcolor) {
        setTitle(-1, title, gravity, textcolor);
        return this;
    }

    public AlertDialog setTitle(int titleId, String title, int gravity, int textcolor) {
        showTitle = true;
        if (gravity != -1) {
            txt_title.setGravity(gravity | Gravity.CENTER_VERTICAL);
        }
        if (textcolor != -1) {
            txt_title.setTextColor(ContextCompat.getColor(context, textcolor));
        }
        if (titleId != -1) {
            txt_title.setText(context.getString(titleId));
        } else if (StringUtils.isNotEmpty(title)) {
            txt_title.setText(title);
        } else {
            txt_title.setText("Title");
        }
        return this;
    }

    public AlertDialog setMsg(int msgId) {
        setMsg(msgId, null);
        return this;
    }

    public AlertDialog setMsg(String msg) {
        setMsg(0, msg);
        return this;
    }

    public AlertDialog setMsg(int msgId, String msg) {
        if (msgId != 0) {
            showMsg = true;
            txt_msg.setText(context.getString(msgId));
        } else if (StringUtils.isNotEmpty(msg)) {
            showMsg = true;
            txt_msg.setText(msg);
        } else {
            showMsg = false;
        }
        return this;
    }

    public AlertDialog setPositiveButton(int textId, View.OnClickListener listener) {
        setPositiveButton(null, textId, listener);
        return this;
    }

    public AlertDialog setPositiveButton(String text, View.OnClickListener listener) {
        setPositiveButton(text, 0, listener);
        return this;
    }

    public AlertDialog setPositiveButton(String text, int textId,
            final View.OnClickListener listener) {
        showPosBtn = true;
        if (textId != 0) {
            tv_pos.setText(context.getString(textId));
        } else if (StringUtils.isNotEmpty(text)) {
            tv_pos.setText(text);
        } else {
            tv_pos.setText("确定");
        }
        tv_pos.setOnClickListener(new View.OnClickListener() {
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

    public AlertDialog setNegativeButton(int textId, View.OnClickListener listener) {
        setNegativeButton(textId, null, listener);
        return this;
    }

    public AlertDialog setNegativeButton(String text, View.OnClickListener listener) {
        setNegativeButton(0, text, listener);
        return this;
    }

    public AlertDialog setNegativeButton(int textId, String text,
            final View.OnClickListener listener) {
        showNegBtn = true;
        if (textId != 0) {
            tv_neg.setText(context.getString(textId));
        } else if (StringUtils.isNotEmpty(text)) {
            tv_neg.setText(text);
        } else {
            tv_neg.setText("取消");
        }

        tv_neg.setOnClickListener(new View.OnClickListener() {
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

    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("Title");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && showNegBtn) {
            layoutBottom.setVisibility(View.VISIBLE);
            vLine.setVisibility(View.VISIBLE);
            tv_pos.setVisibility(View.VISIBLE);
            tv_neg.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            layoutBottom.setVisibility(View.VISIBLE);
            tv_pos.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && showNegBtn) {
            layoutBottom.setVisibility(View.VISIBLE);
            tv_neg.setVisibility(View.VISIBLE);
        }
    }

    public AlertDialog show() {
        setLayout();
        // 调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
        dialog.setCanceledOnTouchOutside(false);
        // 调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
        // dialog.setCancelable(false);
        dialog.show();
        return null;
    }
}
