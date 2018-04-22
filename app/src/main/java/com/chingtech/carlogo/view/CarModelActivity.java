package com.chingtech.carlogo.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chingtech.carlogo.R;
import com.chingtech.carlogo.adapter.ModelAdapter;
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
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

import static com.chingtech.carlogo.Constant.JISHU_APPKEY;

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
 * CarLogo
 * Package com.chingtech.carlogo.view
 * Description:
 * Created by 师春雷
 * Created at 18/4/15 下午12:02
 */
public class CarModelActivity extends BaseActivity implements OnRefreshListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.smartrefresh)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private ModelAdapter adapter;

    private List<CarBean> list = new ArrayList<>();
    private List<CarBean> resuleData;

    private int    type_id;
    private String title;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar).init();
        title = getStringExtra("title");
        initToolBar(toolbar, tvTitle, true, title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh_layout;
    }

    @Override
    protected void initView() {
        type_id = getIntExtra("type_id");

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(
                new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, 2,
                                       R.color.line_color));

        adapter = new ModelAdapter(list);

        adapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("model_id", list.get(position).getId());
            bundle.putString("title", list.get(position).getName());
            openActivity(CarDetailsActivity.class, bundle, false);
        });
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        RetrofitManager.getInstance()
                       .getApiService()
                       .carModel(JISHU_APPKEY, type_id)
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
}
