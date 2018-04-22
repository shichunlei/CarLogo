package com.chingtech.carlogo.view;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.BindString;
import butterknife.BindView;
import com.chingtech.carlogo.R;
import com.chingtech.carlogo.adapter.BrandAdapter;
import com.chingtech.carlogo.http.RetrofitManager;
import com.chingtech.carlogo.model.BaseResult;
import com.chingtech.carlogo.model.CarBean;
import com.chingtech.library.base.activity.BaseActivity;
import com.chingtech.library.utils.LogUtils;
import com.chingtech.library.utils.StringUtils;
import com.chingtech.library.widget.RecycleViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

import static com.chingtech.carlogo.Constant.JISHU_APPKEY;

public class MainActivity extends BaseActivity implements OnRefreshListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindString(R.string.app_name)
    String title;

    @BindView(R.id.smartrefresh)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private BrandAdapter adapter;

    private List<CarBean> list = new ArrayList<>();
    private List<CarBean> resuleData;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar).init();
        initToolBar(toolbar, tvTitle, false, title);
    }

    public void init() {
        // 6.0权限请求
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                                  Manifest.permission.WRITE_EXTERNAL_STORAGE)
                     .subscribe(permission -> {
                         if (permission.granted) {
                             LogUtils.d("permission", permission.name + "开启");
                         } else if (permission.shouldShowRequestPermissionRationale) {
                             LogUtils.d("permission", permission.name + "再次询问");
                         } else {
                             LogUtils.d("permission", permission.name + "被拒绝");
                         }
                     });
        super.init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(
                new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, 2,
                                       R.color.line_color));
        adapter = new BrandAdapter(list);

        adapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", list.get(position).getName());
            bundle.putInt("brand_id", list.get(position).getId());
            openActivity(CarTypeActivity.class, bundle, false);
        });
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        RetrofitManager.getInstance()
                       .getApiService()
                       .allCarBrand(JISHU_APPKEY)
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .compose(this.bindToLifecycle())
                       .subscribe(new Observer<BaseResult<List<CarBean>>>() {
                           @Override
                           public void onSubscribe(Disposable d) {
                           }

                           @Override
                           public void onNext(BaseResult<List<CarBean>> bean) {
                               if (bean.success()) {
                                   LogUtils.i("TAG", bean.getResult().toString());
                                   resuleData = bean.getResult();
                                   if (resuleData.size() == 0) {
                                       mStateView.showEmpty();
                                   } else {
                                       mStateView.showContent();
                                       if (StringUtils.isNotEmpty(list)) {
                                           list.clear();
                                       }
                                       list.addAll(resuleData);
                                       recyclerview.setAdapter(adapter);
                                   }
                               }
                           }

                           @Override
                           public void onError(Throwable e) {
                               mStateView.showRetry();
                               LogUtils.d("TAG", "onError----" + e.getMessage());
                           }

                           @Override
                           public void onComplete() {
                           }
                       });
    }

    @Override
    protected View injectTarget() {
        return refreshLayout;
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        loadData();
        refreshLayout.finishRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem itemReport = menu.findItem(R.id.action_setting);
        itemReport.setOnMenuItemClickListener(item -> {
            openActivity(SettingActivity.class, false);
            return false;
        });
        return true;
    }
}
