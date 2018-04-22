package com.chingtech.library.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chingtech.library.R;
import com.chingtech.library.widget.LoadingView;
import com.chingtech.library.widget.StateView;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by leo on 2016/9/9.
 */
public abstract class BaseFragment extends Fragment implements LifecycleProvider<FragmentEvent> {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected Activity mActivity;

    protected ImmersionBar mImmersionBar;

    protected StateView mStateView;

    Unbinder unbinder;

    private LoadingView progress;

    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(lifecycleSubject);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        //解决fragment重叠问题
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                fragmentTransaction.hide(this);
            } else {
                fragmentTransaction.show(this);
            }
            fragmentTransaction.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initView();
        loadData();
    }

    /**
     * 一些初始化准备
     */
    public void init(View view) {
        unbinder = ButterKnife.bind(this, view);
        mStateView = StateView.inject(injectTarget());
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param textView
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, TextView textView, boolean homeAsUpEnabled,
            String title) {
        if (isImmersionBarEnabled()) {
            initImmersionBar(toolbar);
        }

        toolbar.setTitle("");
        textView.setText(title);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                                           .setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param textView
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, TextView textView, boolean homeAsUpEnabled,
            String title, ImageView topRight, int ivRes) {
        if (isImmersionBarEnabled()) {
            initImmersionBar(toolbar);
        }

        toolbar.setTitle("");
        textView.setText(title);
        topRight.setVisibility(View.VISIBLE);
        topRight.setImageResource(ivRes);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                                           .setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null) {
            mImmersionBar.init();
        }
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar(Toolbar toolbar) {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.titleBar(toolbar)
                     .statusBarDarkFont(true, 0.2f)
                     .keyboardEnable(true)
                     .navigationBarWithKitkatEnable(false)
                     .init();
    }

    /**
     * 设置布局资源
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view操作
     *
     * @return
     */
    protected abstract void initView();

    /**
     * 初始化数据
     *
     * @return
     */
    protected abstract void loadData();

    protected abstract View injectTarget();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    /**
     * 接收前一个页面传递的String值
     *
     * @param key
     * @return
     */
    protected String getStringExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getStringExtra(key);
    }

    /**
     * 接收前一个页面传递的Integer值
     *
     * @param key
     * @return
     */
    protected Integer getIntExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getIntExtra(key, 0);
    }

    /**
     * 接收前一个页面传递的Long值
     *
     * @param key
     * @return
     */
    protected Long getLongExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getLongExtra(key, 0l);
    }

    /**
     * 接收前一个页面传递的Boolean值
     *
     * @param key
     * @return
     */
    protected Boolean getBooleanExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getBooleanExtra(key, false);
    }

    public Object getSerializableExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getSerializableExtra(key);
    }

    public List<Object> getSerializable(String key) {
        Intent receive = getActivity().getIntent();
        return (ArrayList<Object>) receive.getSerializableExtra(key);
    }

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        showActivity(pClass, null, null, null, false, -1);
    }

    /**
     * @param pClass
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, boolean isfinish) {
        showActivity(pClass, null, null, null, isfinish, -1);
    }

    /**
     * @param pClass
     * @param tag
     */
    protected void openActivity(Class<?> pClass, int tag) {
        showActivity(pClass, null, null, null, false, tag);
    }

    /**
     * @param pClass
     * @param key
     * @param value
     * @param tag
     */
    protected void openActivity(Class<?> pClass, String key, Serializable value, int tag) {
        showActivity(pClass, null, key, value, false, tag);
    }

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     * @param key
     * @param value
     */
    protected void openActivity(Class<?> pClass, String key, Serializable value) {
        showActivity(pClass, null, key, value, false, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle, boolean isfinish) {
        showActivity(pClass, pBundle, null, null, isfinish, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     * @param tag
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle, int tag) {
        showActivity(pClass, pBundle, null, null, false, tag);
    }

    /**
     * @param pClass
     * @param key
     * @param value
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, String key, Serializable value, boolean isfinish) {
        showActivity(pClass, null, key, value, isfinish, -1);
    }

    /**
     * @param pClass
     * @param bundle
     * @param key
     * @param value
     * @param isfinish
     * @param tag
     */
    protected void showActivity(Class<?> pClass, Bundle bundle, String key, Serializable value,
            boolean isfinish, int tag) {

        Intent intent = new Intent(mActivity, pClass);

        if (null != key) {
            intent.putExtra(key, value);
        }

        if (null != bundle) {
            intent.putExtras(bundle);
        }

        if (tag == -1) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, tag);
        }

        if (isfinish) {
            getActivity().finish();
        }
    }

    protected void openActivityScaleUp(View view, Class<?> pClass, Bundle bundle) {
        Intent intent = new Intent(mActivity, pClass);
        intent.putExtras(bundle);
        ActivityOptionsCompat compat;
        compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2,
                                                            view.getHeight() / 2, 1000, 1000);
        ActivityCompat.startActivity(mActivity, intent, compat.toBundle());
    }

    protected void openActivityScaleUp(View view, Class<?> pClass) {
        Intent                intent = new Intent(mActivity, pClass);
        ActivityOptionsCompat compat;
        compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2,
                                                            view.getHeight() / 2, 1000, 1000);
        ActivityCompat.startActivity(mActivity, intent, compat.toBundle());
    }

    protected void openActivitySceneTransition(View view, Class<?> pClass, Bundle bundle) {
        Intent intent = new Intent(mActivity, pClass);
        intent.putExtras(bundle);
        ActivityOptionsCompat compat;
        compat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, view,
                                                                    getString(R.string.transition));
        ActivityCompat.startActivity(mActivity, intent, compat.toBundle());
    }

    /**
     * Toast提示
     *
     * @param text
     */
    public void showToast(String text) {
        Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast提示
     *
     * @param resid
     */
    public void showToast(int resid) {
        Toast.makeText(mActivity, getString(resid), Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        if (null == progress) {
            progress = new LoadingView(mActivity);
            progress.setTitleTxt("加载中...");
        }
        progress.show();
    }

    public void dismissLoading() {
        if (null != progress) {
            progress.dismiss();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
        unbinder.unbind();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }
}
