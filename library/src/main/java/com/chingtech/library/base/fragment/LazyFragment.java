package com.chingtech.library.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.ButterKnife;
import com.chingtech.library.widget.LoadingView;
import com.chingtech.library.widget.StateView;

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
 * AndroidBase
 * Package com.chingtech.library.base.fragment
 * Description:
 * Created by 师春雷
 * Created at 17/8/22 上午9:22
 */
public abstract class LazyFragment extends BaseFragment {

    // 是否可见
    protected boolean isVisible;
    // 标志位，标志Fragment已经初始化完成
    protected boolean isPrepared = false;
    // 是否第一次加载
    protected boolean isFirst    = true;

    /**
     * 实现Fragment数据的缓加载
     *
     * @param isVisibleToUser 对用户是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
        }
    }

    protected void onVisible() {
        //加载数据
        loadData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initView();
    }

    /**
     * 一些初始化准备
     */
    public void init(View view) {
        unbinder = ButterKnife.bind(this, view);
        mStateView = StateView.inject(injectTarget());
        //初始化view的各控件
        isPrepared = true;
    }

    /**
     * 懒加载
     *
     * @return
     */
    protected abstract void loadData();
}
