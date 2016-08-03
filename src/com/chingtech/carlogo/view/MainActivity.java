package com.chingtech.carlogo.view;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import com.chingtech.carlogo.R;
import com.chingtech.carlogo.base.BaseActivity;
import com.chingtech.carlogo.base.BaseAdapterHelper;
import com.chingtech.carlogo.base.CommonRecyclerAdapter;
import com.chingtech.carlogo.model.*;
import com.chingtech.carlogo.utils.*;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * @Title: MainActivity
 * @Description: 首页 - 展示全部车型品牌列表
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月2日上午10:50:52
 */
public class MainActivity extends BaseActivity {

	private long mExitTime;

	/** TITLE */
	@ViewInject(id = R.id.tv_title_center)
	private TextView tvTitle;

	@ViewInject(id = R.id.recyclerview)
	private RecyclerView recyclerView;

	private BrandAdapter adapter;

	private List<CarBrand> carBrand = new ArrayList<CarBrand>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	private void init() {
		setTitleBar();
		FinalActivity.initInjectedView(this);
		tvTitle.setText("车系大全");

		recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		getCarBrandList();
	}

	private void getCarBrandList() {
		ListResult result = (ListResult) JsonUtil.fromJson(Constant.ALL_BRAND_DATA, ListResult.class);
		loading.dismiss();
		if (result.getStatus().equals("0")) {
			carBrand = result.getResult();

			adapter = new BrandAdapter(context, R.layout.item_brand_list, carBrand);
			recyclerView.setAdapter(adapter);
		} else {

		}
	}

	private class BrandAdapter extends CommonRecyclerAdapter<CarBrand> {

		public BrandAdapter(Context context, int layoutResId, List<CarBrand> data) {
			super(context, layoutResId, data);
		}

		@Override
		public void onUpdate(BaseAdapterHelper helper, final CarBrand item, int position) {
			helper.setText(R.id.tv_name, item.getName());
			helper.setText(R.id.tv_depth, Utils.getDepthByType(item.getDepth()));
			helper.setImageUrl(R.id.image_logo, item.getLogo(), R.drawable.icon_loading);
			helper.setOnClickListener(R.id.layout_brand, new OnClickListener() {

				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString("name", item.getName());
					bundle.putInt("id", item.getId());
					openActivity(CarModelActivity.class, bundle, false);
				}
			});
		}
	}

	// 按下菜单键时
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// 判断两次点击的时间间隔（默认设置为2秒）
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				showToast("再按一次退出程序！");
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
