package com.chingtech.carlogo.view;

import android.content.pm.PackageManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chingtech.carlogo.R;
import com.chingtech.library.base.activity.BaseActivity;
import com.tencent.bugly.beta.Beta;

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
 * Created at 18/4/22 下午5:15
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar).init();
        initToolBar(toolbar, tvTitle, true, "设置");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        String versionName = getVersionName();
        tvVersion.setText(versionName);
    }

    @Override
    protected void loadData() {
    }

    @OnClick({R.id.layout_version})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_version:
                Beta.checkUpgrade();
                break;
        }
    }

    @Override
    protected View injectTarget() {
        return layout;
    }

    /**
     * 获取版本名
     *
     * @return
     */
    private String getVersionName() {
        String name;
        try {
            name = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            name = "";
        }
        return name;
    }
}
