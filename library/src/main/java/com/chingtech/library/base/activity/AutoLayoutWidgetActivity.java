package com.chingtech.library.base.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.chingtech.library.widget.AutoAppBarLayout;
import com.chingtech.library.widget.AutoCardView;
import com.chingtech.library.widget.AutoCollapsingToolbarLayout;
import com.chingtech.library.widget.AutoNestedScrollView;
import com.chingtech.library.widget.AutoToolbar;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Date：2017/9/20 0020-上午 10:08
 * Author: zxm
 * Description:
 */
public class AutoLayoutWidgetActivity extends AutoLayoutActivity {

    private static final String LAYOUT_APPBARLAYOUT            = "android.support.design.widget.AppBarLayout";
    private static final String LAYOUT_CARDVIEW
                                                               = "android.support.v7.widget.CardView";
    private static final String LAYOUT_COLLAPSINGTOOLBARLAYOUT
                                                               = "android.support.design.widget.CollapsingToolbarLayout";
    private static final String LAYOUT_NESTEDSCROLLVIEW
                                                               = "android.support.v4.widget.NestedScrollView";
    private static final String LAYOUT_TOOLBAR
                                                               = "android.support.v7.widget.Toolbar";


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_APPBARLAYOUT)) {
            view = new AutoAppBarLayout(context, attrs);
        }

        if (name.equals(LAYOUT_CARDVIEW)) {
            view = new AutoCardView(context, attrs);
        }

        if (name.equals(LAYOUT_COLLAPSINGTOOLBARLAYOUT)) {
            view = new AutoCollapsingToolbarLayout(context, attrs);
        }

        if (name.equals(LAYOUT_TOOLBAR)) {
            view = new AutoToolbar(context, attrs);
        }
        if (name.equals(LAYOUT_NESTEDSCROLLVIEW)) {
            view = new AutoNestedScrollView(context, attrs);
        }

        if (view != null) {
            return view;
        }

        return super.onCreateView(name, context, attrs);
    }
}
