package com.chingtech.library.base.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chingtech.library.R;
import com.chingtech.library.base.application.BaseApplication;
import com.chingtech.library.widget.StateView;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by leo on 2016/9/23.
 */
public abstract class BaseActivity extends AutoLayoutWidgetActivity
        implements LifecycleProvider<ActivityEvent> {

    protected Context context;

    protected StateView mStateView;

    private long exitTime = 0l;
    private Unbinder unbinder;

    private   InputMethodManager imm;
    protected ImmersionBar       mImmersionBar;

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT
                < Build.VERSION_CODES.O) { // 暂时解决8.0上面 Only fullscreen activities can request orientation 异常
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
            // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
        }

        lifecycleSubject.onNext(ActivityEvent.CREATE);

        BaseApplication.activities.add(this);

        setContentView(getLayoutId());

        init();
        initView();
        loadData();
    }

    /**
     * 一些初始化准备
     */
    public void init() {
        unbinder = ButterKnife.bind(this);
        context = this;
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        mStateView = StateView.inject(injectTarget());
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {

        toolbar.setTitle(title);
        assert toolbar != null;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param tvTitle
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, TextView tvTitle, boolean homeAsUpEnabled,
            String title) {

        toolbar.setTitle("");
        tvTitle.setText(title);
        assert toolbar != null;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param tvTitle
     * @param homeAsUpEnabled
     * @param title
     * @param tvTitleRight
     * @param titleRight
     */
    public void initToolBar(Toolbar toolbar, TextView tvTitle, boolean homeAsUpEnabled,
            String title, TextView tvTitleRight, String titleRight) {

        toolbar.setTitle("");
        tvTitle.setText(title);
        assert toolbar != null;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setText(titleRight);
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param tvTitle
     * @param homeAsUpEnabled
     * @param title
     * @param ivTitleRight
     * @param titleRes
     */
    public void initToolBar(Toolbar toolbar, TextView tvTitle, boolean homeAsUpEnabled,
            String title, ImageView ivTitleRight, int titleRes) {

        toolbar.setTitle("");
        tvTitle.setText(title);
        assert toolbar != null;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ivTitleRight.setVisibility(View.VISIBLE);
        ivTitleRight.setImageResource(titleRes);
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param tvTitle
     * @param menuRes
     * @param title
     * @param ivTitleRight
     * @param titleRes
     */
    public void initToolBar(Toolbar toolbar, TextView tvTitle, int menuRes, String title,
            ImageView ivTitleRight, int titleRes) {

        toolbar.setTitle("");
        tvTitle.setText(title);
        assert toolbar != null;
        toolbar.setNavigationIcon(menuRes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ivTitleRight.setVisibility(View.VISIBLE);
        ivTitleRight.setImageResource(titleRes);
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

    /**
     * 接收前一个页面传递的String值
     *
     * @param key
     * @return
     */
    public String getStringExtra(String key) {
        Intent receive = getIntent();
        return receive.getStringExtra(key);
    }

    /**
     * 接收前一个页面传递的Integer值
     *
     * @param key
     * @return
     */
    public Integer getIntExtra(String key) {
        Intent receive = getIntent();
        return receive.getIntExtra(key, 0);
    }

    /**
     * 接收前一个页面传递的Long值
     *
     * @param key
     * @return
     */
    public Long getLongExtra(String key) {
        Intent receive = getIntent();
        return receive.getLongExtra(key, 0l);
    }

    /**
     * 接收前一个页面传递的Boolean值
     *
     * @param key
     * @return
     */
    protected Boolean getBooleanExtra(String key) {
        Intent receive = getIntent();
        return receive.getBooleanExtra(key, false);
    }

    public Object getSerializableExtra(String key) {
        Intent receive = getIntent();
        return receive.getSerializableExtra(key);
    }

    public List<Object> getSerializable(String key) {
        Intent receive = getIntent();
        return (ArrayList<Object>) receive.getSerializableExtra(key);
    }

    /**
     * 通过类名启动Activity，是否结束本页面
     *
     * @param pClass
     * @param tag
     */
    protected void openActivity(Class<?> pClass, int tag) {
        showActivity(pClass, null, null, null, false, tag, -1);
    }

    /**
     * 通过类名启动Activity，是否结束本页面
     *
     * @param pClass
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, boolean isfinish) {
        showActivity(pClass, null, null, null, isfinish, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且携带数据
     *
     * @param pClass
     * @param key
     * @param value
     */
    public void openActivity(Class<?> pClass, String key, Serializable value) {
        showActivity(pClass, null, key, value, false, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且携带单个数据
     *
     * @param pClass
     * @param key
     * @param value
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, String key, Serializable value, boolean isfinish) {
        showActivity(pClass, null, key, value, isfinish, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且携带单个数据
     *
     * @param pClass
     * @param key
     * @param value
     * @param tag
     */
    public void openActivity(Class<?> pClass, String key, Serializable value, int tag) {
        showActivity(pClass, null, key, value, false, tag, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Flags数据
     *
     * @param pClass
     * @param flags
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, int flags, boolean isfinish) {
        showActivity(pClass, null, null, null, isfinish, -1, flags);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param bundle
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, Bundle bundle, boolean isfinish) {
        showActivity(pClass, bundle, null, null, isfinish, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     * @param tag
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle, int tag) {
        showActivity(pClass, pBundle, null, null, false, tag, -1);
    }

    private void showActivity(Class<?> pClass, Bundle bundle, String key, Serializable value,
            boolean isfinish, int tag, int flags) {

        Intent intent = new Intent(context, pClass);

        if (null != key) {
            intent.putExtra(key, value);
        }

        if (null != bundle) {
            intent.putExtras(bundle);
        }

        if (flags != -1) {
            intent.setFlags(flags);
        }

        if (tag == -1) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, tag);
        }

        hideSoftKeyBoard();
        if (isfinish) {
            this.finish();
        }
    }

    protected void openActivityScaleUp(View view, Class<?> pClass) {
        Intent                intent = new Intent(this, pClass);
        ActivityOptionsCompat compat;
        compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2,
                                                            view.getHeight() / 2, 0, 0);
        ActivityCompat.startActivity(this, intent, compat.toBundle());
    }

    protected void openActivityScaleUp(View view, Class<?> pClass, Bundle bundle) {
        Intent intent = new Intent(this, pClass);
        intent.putExtras(bundle);
        ActivityOptionsCompat compat;
        compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2,
                                                            view.getHeight() / 2, 0, 0);
        ActivityCompat.startActivity(this, intent, compat.toBundle());
    }

    protected void openActivitySceneTransition(View view, Class<?> pClass, Bundle bundle) {
        Intent intent = new Intent(this, pClass);
        intent.putExtras(bundle);
        ActivityOptionsCompat compat;
        compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view,
                                                                    getString(R.string.transition));
        ActivityCompat.startActivity(this, intent, compat.toBundle());
    }

    /**
     * Toast提示
     *
     * @param text
     */
    public void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast提示
     *
     * @param resid
     */
    public void showToast(int resid) {
        Toast.makeText(context, BaseApplication.context.getResources().getText(resid),
                       Toast.LENGTH_SHORT).show();
    }

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showToast("再按一次退出！");
            exitTime = System.currentTimeMillis();
        } else {
            BaseApplication.exit();
        }
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        unbinder.unbind();
        this.imm = null;
        if (mImmersionBar != null) {
            mImmersionBar.destroy();  //在BaseActivity里销毁
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
