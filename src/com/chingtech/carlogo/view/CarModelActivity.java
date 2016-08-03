package com.chingtech.carlogo.view;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.content.Context;
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
import com.chingtech.carlogo.base.CommonAdapter;
import com.chingtech.carlogo.base.CommonRecyclerAdapter;
import com.chingtech.carlogo.model.BrandList;
import com.chingtech.carlogo.model.CarBrand;
import com.chingtech.carlogo.model.ListResult;
import com.chingtech.carlogo.utils.*;

/**
 * 
 * @Title: CarModelActivity
 * @Description: 品牌下所有车型
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月2日下午1:27:56
 */
public class CarModelActivity extends BaseActivity {

	/** 返回 */
	@ViewInject(id = R.id.img_title_left, click = "back")
	private ImageView imgBack;
	/** TITLE */
	@ViewInject(id = R.id.tv_title_center)
	private TextView tvTitle;

	@ViewInject(id = R.id.recyclerview)
	private RecyclerView recyclerView;

	private List<CarBrand> carBrand = new ArrayList<CarBrand>();

	private ModelAdapter adapter;

	private int parentid;
	private String appkey = Constant.appkey;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_model);

		init();
	}

	private void init() {
		setTitleBar();
		FinalActivity.initInjectedView(this);
		tvTitle.setText(getStringExtra("name"));
		imgBack.setImageResource(R.drawable.icon_back_white);

		parentid = getIntExtra("id");

		recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		getCarModelList(appkey, parentid);
	}

	public void back(View v) {
		finish();
	}

	private void getCarModelList(String appkey, int parentid) {
		loading.show();

		AjaxParams params = new AjaxParams();

		params.put("parentid", parentid);
		params.put("appkey", appkey);

		FinalHttp fh = new FinalHttp();
		fh.configTimeout(ApiUtil.TIME_OUT);
		fh.get(ApiUtil.ROOT_URL + ApiUtil.CAR_MODEL_LIST, params, new AjaxCallBack<Object>() {

			@Override
			public void onLoading(long count, long current) {
				super.onLoading(count, current);
			}

			@Override
			public void onSuccess(Object t) {
				super.onSuccess(t);
				String str = t.toString();
				ListResult result = (ListResult) JsonUtil.fromJson(str, ListResult.class);
				loading.dismiss();
				if (result.getStatus().equals("0")) {
					carBrand = result.getResult();

					adapter = new ModelAdapter(context, R.layout.item_model, carBrand);
					recyclerView.setAdapter(adapter);
				} else {

				}
			}

			@Override
			public void onFailure(Throwable t, int errorCode, String strMsg) {
				super.onFailure(t, errorCode, strMsg);
				loading.dismiss();
				showToast("");
			}
		});
	}

	private class ModelAdapter extends CommonRecyclerAdapter<CarBrand> {

		public ModelAdapter(Context context, int layoutResId, List<CarBrand> data) {
			super(context, layoutResId, data);
		}

		@Override
		public void onUpdate(BaseAdapterHelper helper, final CarBrand item, int position) {
			helper.setText(R.id.tv_name, item.getName());

			helper.setAdapter(R.id.listview, new CommonAdapter<BrandList>(context, R.layout.item_sub_model,
					item.getCarlist()) {

				@Override
				public void onUpdate(BaseAdapterHelper helper, final BrandList item, int position) {
					helper.setText(R.id.tv_name, item.getName());
					helper.setText(R.id.tv_fullname, item.getFullname());
					helper.setImageUrl(R.id.image_logo, item.getLogo(), R.drawable.icon_loading);
					helper.setText(R.id.tv_salestate, item.getSalestate());
					helper.setOnClickListener(R.id.layout_submodel, new OnClickListener() {

						@Override
						public void onClick(View v) {
							Bundle bundle = new Bundle();
							bundle.putString("name", item.getFullname());
							bundle.putString("list", item.getList().toString());
							openActivity(ModelListActivity.class, bundle, false);
						}
					});
				}
			});
		}
	}
}
