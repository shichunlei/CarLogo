package com.chingtech.carlogo.view;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.chingtech.carlogo.R;
import com.chingtech.carlogo.base.BaseActivity;
import com.chingtech.carlogo.base.BaseAdapterHelper;
import com.chingtech.carlogo.base.CommonRecyclerAdapter;
import com.chingtech.carlogo.model.CarBrand;
import com.chingtech.carlogo.model.CarList;
import com.chingtech.carlogo.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @Title: ModelListActivity
 * @Description: 品牌下所有车型
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月2日下午3:28:59
 */
public class ModelListActivity extends BaseActivity {

	/** 返回 */
	@ViewInject(id = R.id.img_title_left, click = "back")
	private ImageView imgBack;
	/** TITLE */
	@ViewInject(id = R.id.tv_title_center)
	private TextView tvTitle;

	@ViewInject(id = R.id.recyclerview)
	private RecyclerView recyclerView;

	List<CarBrand> carBrand = new ArrayList<CarBrand>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_model_list);

		init();
	}

	private void init() {
		setTitleBar();
		FinalActivity.initInjectedView(this);
		tvTitle.setText(getStringExtra("name"));
		imgBack.setImageResource(R.drawable.icon_back_white);

		recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		getCarModelList();
	}

	public void back(View v) {
		finish();
	}

	@SuppressWarnings("unchecked")
	private void getCarModelList() {
		String str = getStringExtra("list");
		List<CarList> list = (List<CarList>) JsonUtil.fromJson(str, new TypeToken<List<CarList>>() {
		});

		recyclerView.setAdapter(new CommonRecyclerAdapter<CarList>(context, R.layout.item_model_list, list) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, final CarList item, int position) {
				helper.setText(R.id.tv_name, item.getName());
				helper.setText(R.id.tv_sale, item.getProductionstate() + "/" + item.getSalestate());
				helper.setText(R.id.tv_price, "￥" + item.getPrice());
				helper.setText(R.id.tv_year, item.getYeartype());
				helper.setText(R.id.tv_sizetype, item.getSizetype());
				helper.setOnClickListener(R.id.layout_model_list, new OnClickListener() {

					@Override
					public void onClick(View v) {
						Bundle bundle = new Bundle();
						bundle.putString("name", item.getName());
						bundle.putInt("id", item.getId());
						openActivity(CarDetailActivity.class, bundle, false);
					}
				});
			}
		});
	}
}
