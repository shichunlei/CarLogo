package com.chingtech.carlogo.view;

import im.fir.sdk.FIR;
import im.fir.sdk.VersionCheckCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.HttpHandler;

import com.chingtech.carlogo.R;
import com.chingtech.carlogo.base.BaseActivity;
import com.chingtech.carlogo.base.BaseAdapterHelper;
import com.chingtech.carlogo.base.CommonRecyclerAdapter;
import com.chingtech.carlogo.model.*;
import com.chingtech.carlogo.utils.*;
import com.chingtech.carlogo.widget.AlertDialog;
import com.chingtech.carlogo.widget.CommonProgressDialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

	private int versionCode = 1;

	private HttpHandler<File> handler;
	private CommonProgressDialog dialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		update();
		init();
	}

	private void init() {
		setTitleBar();
		FinalActivity.initInjectedView(this);
		tvTitle.setText("车系大全");

		versionCode = Utils.getVersionCode(context);

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

	public void update() {
		FIR.checkForUpdateInFIR(Constant.api_token, new VersionCheckCallback() {
			@Override
			public void onSuccess(String versionJson) {
				Log.i("fir", "check from fir.im success! " + "\n" + versionJson);
				final Version version = (Version) JsonUtil.fromJson(versionJson, Version.class);
				final String UPDATE_APK_NAME = Utils.getSDPath() + version.getName() + "-"
						+ version.getVersionShort() + ".apk";

				int webVersionCode = version.getVersion();
				if (webVersionCode > versionCode) {
					new AlertDialog(context).builder().setTitle("软件更新")
							.setMsg("有新版本，是否更新？\n更新内容：\n" + version.getChangelog())
							.setPositiveButton("立刻更新", new OnClickListener() {

								@Override
								public void onClick(View v) {
									dialog = new CommonProgressDialog(context);
									dialog.builder();
									downFile(version.getInstallUrl(), UPDATE_APK_NAME);
								}
							}).setNegativeButton("稍后再说", null).show();
				} else {

				}
			}

			@Override
			public void onFail(Exception exception) {
				Log.i("fir", "check fir.im fail! " + exception.getMessage());
			}

			@Override
			public void onStart() {
				Log.i("fir", "check fir.im start! 正在获取");
			}

			@Override
			public void onFinish() {
				Log.i("fir", "check fir.im finish! 获取完成");
			}
		});
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

	/**
	 * 下载apk
	 */
	private void downFile(String url, final String fileName) {
		dialog.show();
		FinalHttp fh = new FinalHttp();
		handler = fh.download(url, fileName, new AjaxCallBack<File>() {
			@Override
			public void onLoading(long count, long current) {
				super.onLoading(count, current);
				// 设置进度条最大值
				dialog.setMax((int) count);
				// 设置ProgressBar当前值
				dialog.setProgress((int) current);
			}

			@Override
			public void onSuccess(File t) {
				if (t != null) {
					handler.stop();
					dialog.dismiss();
					Utils.update(context, fileName);
				}
			}

			@Override
			public void onFailure(Throwable t, int errorCode, String strMsg) {
				super.onFailure(t, errorCode, strMsg);
				dialog.dismiss();
			}
		});
	}

}
