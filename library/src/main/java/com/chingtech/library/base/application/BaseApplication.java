package com.chingtech.library.base.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.chingtech.library.R;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import java.util.LinkedList;
import java.util.List;

/**
 * AndroidBase
 * Package com.chingtech.utils
 * Description:
 * Created by 师春雷
 * Created at 17/7/29 下午2:21
 */
public class BaseApplication extends Application {

    public static Context context;

    public static List<Activity> activities = new LinkedList<>();

    private static boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                MaterialHeader header = new MaterialHeader(context);
                header.setShowBezierWave(true);
                header.setPrimaryColors(ContextCompat.getColor(context, R.color.window_background),
                                        ContextCompat.getColor(context, android.R.color.white));
                return header;
            }
        });

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                BallPulseFooter footer = new BallPulseFooter(context);
                footer.setPrimaryColors(ContextCompat.getColor(context, R.color.colorPrimary),
                                        ContextCompat.getColor(context, android.R.color.white));
                footer.setSpinnerStyle(SpinnerStyle.Scale);//设置为拉伸模式
                return footer;
            }
        });
    }

    public static boolean isDebug() {
        return isDebug;
    }

    /**
     * 完全退出
     * 一般用于“退出程序”功能
     */
    public static void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }
}
