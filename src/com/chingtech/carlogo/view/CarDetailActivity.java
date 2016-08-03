package com.chingtech.carlogo.view;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chingtech.carlogo.R;
import com.chingtech.carlogo.base.BaseActivity;
import com.chingtech.carlogo.base.BaseAdapterHelper;
import com.chingtech.carlogo.base.CommonAdapter;
import com.chingtech.carlogo.model.*;
import com.chingtech.carlogo.utils.*;
import com.chingtech.carlogo.widget.MyGridView;
import com.chingtech.carlogo.widget.MyListView;

/**
 * 
 * @Title: CarDetailActivity
 * @Description: 车型详情
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月3日下午3:23:11
 */
public class CarDetailActivity extends BaseActivity {

	/** 返回 */
	@ViewInject(id = R.id.img_title_left, click = "back")
	private ImageView imgBack;
	/** TITLE */
	@ViewInject(id = R.id.tv_title_center)
	private TextView tvTitle;

	@ViewInject(id = R.id.basic_listview)
	private MyListView basicList;

	@ViewInject(id = R.id.gridview)
	private MyGridView gridview;
	@ViewInject(id = R.id.body_listview)
	private MyListView bodyList;

	@ViewInject(id = R.id.engine_listview)
	private MyListView engineList;

	@ViewInject(id = R.id.tv_gearbox_gearbox)
	private TextView gearbox;
	@ViewInject(id = R.id.tv_gearbox_shiftpaddles)
	private TextView shiftpaddles;

	@ViewInject(id = R.id.chassisbrake_listview)
	private MyListView chassisbrakeList;

	@ViewInject(id = R.id.safe_listview)
	private MyListView safeList;

	@ViewInject(id = R.id.tv_wheel_fronttiresize)
	private TextView fronttiresize;
	@ViewInject(id = R.id.tv_wheel_reartiresize)
	private TextView reartiresize;
	@ViewInject(id = R.id.tv_wheel_sparetiretype)
	private TextView sparetiretype;
	@ViewInject(id = R.id.tv_wheel_hubmaterial)
	private TextView hubmaterial;

	@ViewInject(id = R.id.drivingauxiliary_listview)
	private MyListView drivingauxiliaryList;

	@ViewInject(id = R.id.doormirror_listview)
	private MyListView doorList;

	@ViewInject(id = R.id.internalconfig_listview)
	private MyListView internalconfigList;

	@ViewInject(id = R.id.seat_listview)
	private MyListView seatList;

	@ViewInject(id = R.id.entcom_listview)
	private MyListView entcomList;

	@ViewInject(id = R.id.light_listview)
	private MyListView lightList;

	@ViewInject(id = R.id.air_listview)
	private MyListView airList;

	@ViewInject(id = R.id.tv_actualtest_accelerationtime100)
	private TextView accelerationtime100;
	@ViewInject(id = R.id.tv_actualtest_brakingdistance)
	private TextView brakingdistance;

	private String appkey = Constant.appkey;
	private int carid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		init();
	}

	private void init() {
		setTitleBar();
		FinalActivity.initInjectedView(this);
		tvTitle.setText(getStringExtra("name"));
		imgBack.setImageResource(R.drawable.icon_back_white);

		carid = getIntExtra("id");

		getCarDetail(appkey, carid);
	}

	public void back(View v) {
		finish();
	}

	private void getCarDetail(String appkey, int carid) {
		loading.show();

		AjaxParams params = new AjaxParams();

		params.put("carid", carid);
		params.put("appkey", appkey);

		FinalHttp fh = new FinalHttp();
		fh.configTimeout(ApiUtil.TIME_OUT);
		fh.get(ApiUtil.ROOT_URL + ApiUtil.CAR_DETAIL, params, new AjaxCallBack<Object>() {

			@Override
			public void onLoading(long count, long current) {
				super.onLoading(count, current);
			}

			@Override
			public void onSuccess(Object t) {
				super.onSuccess(t);
				String str = t.toString();
				Result result = (Result) JsonUtil.fromJson(str, Result.class);
				loading.dismiss();
				if (result.getStatus().equals("0")) {
					Basic basic = result.getResult().getBasic();
					setBasicData(basic);

					Body body = result.getResult().getBody();
					setBodyData(body);

					Engine engine = result.getResult().getEngine();
					setEngineData(engine);

					Gearbox _gearbox = result.getResult().getGearbox();
					gearbox.setText(_gearbox.getGearbox());
					shiftpaddles.setText(_gearbox.getShiftpaddles());

					Chassisbrake chassisbrake = result.getResult().getChassisbrake();
					setChassisbrakeData(chassisbrake);

					Safe safe = result.getResult().getSafe();
					setSafeData(safe);

					Wheel wheel = result.getResult().getWheel();
					fronttiresize.setText(wheel.getFronttiresize());
					reartiresize.setText(wheel.getReartiresize());
					sparetiretype.setText(wheel.getSparetiretype());
					hubmaterial.setText(wheel.getHubmaterial());

					Drivingauxiliary drivingauxiliary = result.getResult().getDrivingauxiliary();
					setDrivingauxiliaryData(drivingauxiliary);

					Doormirror door = result.getResult().getDoormirror();
					setDoormirrorData(door);

					Light light = result.getResult().getLight();
					setLightData(light);

					Internalconfig internal = result.getResult().getInternalconfig();
					setInternalconfigData(internal);

					Seat seat = result.getResult().getSeat();
					setSeatData(seat);

					Entcom entcom = result.getResult().getEntcom();
					setEntcomData(entcom);

					Aircondrefrigerator air = result.getResult().getAircondrefrigerator();
					setAircondrefrigeratorData(air);

					Actualtest actualtest = result.getResult().getActualtest();
					accelerationtime100.setText(actualtest.getAccelerationtime100());
					brakingdistance.setText(actualtest.getBrakingdistance());
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

	private void setBasicData(Basic basic) {
		String[] basic_key = getResources().getStringArray(R.array.basic_key);
		String[] basic_value = { basic.getPrice(), basic.getSaleprice(), basic.getWarrantypolicy(),
				basic.getVechiletax(), basic.getDisplacement(), basic.getGearbox(),
				basic.getComfuelconsumption(), basic.getUserfuelconsumption(),
				basic.getOfficialaccelerationtime100(), basic.getTestaccelerationtime100(),
				basic.getMaxspeed(), basic.getSeatnum() };

		List<DataModel> basicData = new ArrayList<DataModel>();
		for (int i = 0; i < basic_key.length; i++) {
			basicData.add(new DataModel(basic_key[i], basic_value[i]));
		}

		basicList.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, basicData) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
				helper.setText(R.id.tv_basic_key, item.getKey());
				helper.setText(R.id.tv_basic_value, item.getValue());
			}
		});
	}

	private void setBodyData(Body body) {
		List<String> colors = Utils.getColors(body.getColor());
		gridview.setNumColumns(colors.size());
		gridview.setAdapter(new CommonAdapter<String>(context, R.layout.item_colors, colors) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, String item, int position) {
				helper.setBackgroundColor(R.id.tv_color, item);
			}
		});

		String[] body_key = getResources().getStringArray(R.array.body_key);
		String[] body_value = { body.getLen() + "*" + body.getWidth() + "*" + body.getHeight(),
				body.getWheelbase(), body.getFronttrack(), body.getReartrack(), body.getWeight(),
				body.getFullweight(), body.getMingroundclearance(), body.getApproachangle(),
				body.getDepartureangle(), body.getLuggagevolume(), body.getLuggagemode(),
				body.getLuggageopenmode(), body.getInductionluggage(), body.getDoornum(), body.getTooftype(),
				body.getHoodtype(), body.getRoofluggagerack(), body.getSportpackage() };

		List<DataModel> bodyData = new ArrayList<DataModel>();
		for (int i = 0; i < body_key.length; i++) {
			bodyData.add(new DataModel(body_key[i], body_value[i]));
		}

		bodyList.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, bodyData) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
				helper.setText(R.id.tv_basic_key, item.getKey());
				helper.setText(R.id.tv_basic_value, item.getValue());
			}
		});
	}

	private void setEngineData(Engine engine) {
		String[] engine_key = getResources().getStringArray(R.array.engine_key);

		String[] engine_value = { engine.getPosition(), engine.getModel(), engine.getDisplacement(),
				engine.getDisplacementml(), engine.getIntakeform(), engine.getCylinderarrangetype(),
				engine.getCylindernum(), engine.getValvetrain(), engine.getValvestructure(),
				engine.getCompressionratio(), engine.getBore(), engine.getStroke(),
				engine.getMaxhorsepower(), engine.getMaxpower(), engine.getMaxpowerspeed(),
				engine.getMaxtorque(), engine.getMaxtorquespeed(), engine.getFueltype(),
				engine.getFuelgrade(), engine.getFuelmethod(), engine.getFueltankcapacity(),
				engine.getCylinderheadmaterial(), engine.getCylinderbodymaterial(),
				engine.getEnvironmentalstandards(), engine.getStartstopsystem() };

		List<DataModel> engineData = new ArrayList<DataModel>();
		for (int i = 0; i < engine_key.length; i++) {
			engineData.add(new DataModel(engine_key[i], engine_value[i]));
		}

		engineList.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, engineData) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
				helper.setText(R.id.tv_basic_key, item.getKey());
				helper.setText(R.id.tv_basic_value, item.getValue());
			}
		});
	}

	private void setChassisbrakeData(Chassisbrake chassisbrake) {
		String[] chassisbrake_key = getResources().getStringArray(R.array.chassisbrake_key);
		String[] chassisbrake_value = { chassisbrake.getBodystructure(), chassisbrake.getPowersteering(),
				chassisbrake.getFrontbraketype(), chassisbrake.getRearbraketype(),
				chassisbrake.getParkingbraketype(), chassisbrake.getDrivemode(),
				chassisbrake.getAirsuspension(), chassisbrake.getAdjustablesuspension(),
				chassisbrake.getFrontsuspensiontype(), chassisbrake.getRearsuspensiontype(),
				chassisbrake.getCenterdifferentiallock() };

		List<DataModel> chassisbrakeData = new ArrayList<DataModel>();
		for (int i = 0; i < chassisbrake_key.length; i++) {
			chassisbrakeData.add(new DataModel(chassisbrake_key[i], chassisbrake_value[i]));
		}

		chassisbrakeList.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data,
				chassisbrakeData) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
				helper.setText(R.id.tv_basic_key, item.getKey());
				helper.setText(R.id.tv_basic_value, item.getValue());
			}
		});
	}

	private void setSafeData(Safe safe) {
		String[] safe_key = getResources().getStringArray(R.array.safe_key);
		String[] safe_value = { safe.getAirbagdrivingposition(), safe.getAirbagfrontpassenger(),
				safe.getAirbagfrontside(), safe.getAirbagfronthead(), safe.getAirbagknee(),
				safe.getAirbagrearside(), safe.getAirbagrearhead(), safe.getSafetybeltprompt(),
				safe.getSafetybeltlimiting(), safe.getSafetybeltpretightening(),
				safe.getFrontsafetybeltadjustment(), safe.getRearsafetybelt(),
				safe.getTirepressuremonitoring(), safe.getZeropressurecontinued(), safe.getCentrallocking(),
				safe.getChildlock(), safe.getRemotekey(), safe.getKeylessentry(), safe.getKeylessstart(),
				safe.getEngineantitheft() };

		List<DataModel> safeData = new ArrayList<DataModel>();
		for (int i = 0; i < safe_key.length; i++) {
			safeData.add(new DataModel(safe_key[i], safe_value[i]));
		}

		safeList.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, safeData) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
				helper.setText(R.id.tv_basic_key, item.getKey());
				helper.setText(R.id.tv_basic_value, item.getValue());
			}
		});
	}

	private void setDrivingauxiliaryData(Drivingauxiliary driving) {
		String[] driving_key = getResources().getStringArray(R.array.driving_key);
		String[] driving_value = { driving.getAbs(), driving.getEbd(), driving.getBrakeassist(),
				driving.getTractioncontrol(), driving.getEsp(), driving.getEps(),
				driving.getAutomaticparking(), driving.getHillstartassist(), driving.getHilldescent(),
				driving.getFrontparkingradar(), driving.getReversingradar(), driving.getReverseimage(),
				driving.getPanoramiccamera(), driving.getCruisecontrol(), driving.getAdaptivecruise(),
				driving.getGps(), driving.getAutomaticparkingintoplace(), driving.getLdws(),
				driving.getActivebraking(), driving.getIntegralactivesteering(),
				driving.getNightvisionsystem(), driving.getBlindspotdetection() };

		List<DataModel> drivingData = new ArrayList<DataModel>();
		for (int i = 0; i < driving_key.length; i++) {
			drivingData.add(new DataModel(driving_key[i], driving_value[i]));
		}

		drivingauxiliaryList
				.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, drivingData) {

					@Override
					public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
						helper.setText(R.id.tv_basic_key, item.getKey());
						helper.setText(R.id.tv_basic_value, item.getValue());
					}
				});
	}

	private void setDoormirrorData(Doormirror door) {
		String[] door_key = getResources().getStringArray(R.array.door_key);
		String[] door_value = { door.getOpenstyle(), door.getElectricwindow(), door.getUvinterceptingglass(),
				door.getPrivacyglass(), door.getAntipinchwindow(), door.getSkylightopeningmode(),
				door.getSkylightstype(), door.getRearwindowsunshade(), door.getRearsidesunshade(),
				door.getRearwiper(), door.getSensingwiper(), door.getElectricpulldoor(),
				door.getRearmirrorwithturnlamp(), door.getExternalmirrormemory(),
				door.getExternalmirrorheating(), door.getExternalmirrorfolding(),
				door.getExternalmirroradjustment(), door.getRearviewmirrorantiglare(),
				door.getSunvisormirror() };

		List<DataModel> doorData = new ArrayList<DataModel>();
		for (int i = 0; i < door_key.length; i++) {
			doorData.add(new DataModel(door_key[i], door_value[i]));
		}

		doorList.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, doorData) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
				helper.setText(R.id.tv_basic_key, item.getKey());
				helper.setText(R.id.tv_basic_value, item.getValue());
			}
		});
	}

	private void setLightData(Light light) {
		String[] light_key = getResources().getStringArray(R.array.light_key);
		String[] light_value = { light.getHeadlighttype(), light.getOptionalheadlighttype(),
				light.getHeadlightautomaticopen(), light.getHeadlightautomaticclean(),
				light.getHeadlightdelayoff(), light.getHeadlightdynamicsteering(),
				light.getHeadlightilluminationadjustment(), light.getHeadlightdimming(),
				light.getFrontfoglight(), light.getReadinglight(), light.getInteriorairlight(),
				light.getDaytimerunninglight(), light.getLedtaillight(), light.getLightsteeringassist() };

		List<DataModel> lightData = new ArrayList<DataModel>();
		for (int i = 0; i < light_key.length; i++) {
			lightData.add(new DataModel(light_key[i], light_value[i]));
		}

		lightList.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, lightData) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
				helper.setText(R.id.tv_basic_key, item.getKey());
				helper.setText(R.id.tv_basic_value, item.getValue());
			}
		});
	}

	private void setInternalconfigData(Internalconfig internal) {
		String[] internal_key = getResources().getStringArray(R.array.internal_key);
		String[] internal_value = { internal.getSteeringwheelbeforeadjustment(),
				internal.getSteeringwheelupadjustment(), internal.getSteeringwheeladjustmentmode(),
				internal.getSteeringwheelmemory(), internal.getSteeringwheelmaterial(),
				internal.getSteeringwheelmultifunction(), internal.getSteeringwheelheating(),
				internal.getComputerscreen(), internal.getHuddisplay(), internal.getInteriorcolor(),
				internal.getRearcupholder(), internal.getSupplyvoltage() };

		List<DataModel> internalData = new ArrayList<DataModel>();
		for (int i = 0; i < internal_key.length; i++) {
			internalData.add(new DataModel(internal_key[i], internal_value[i]));
		}

		internalconfigList
				.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, internalData) {

					@Override
					public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
						helper.setText(R.id.tv_basic_key, item.getKey());
						helper.setText(R.id.tv_basic_value, item.getValue());
					}
				});
	}

	private void setSeatData(Seat seat) {
		String[] seat_key = getResources().getStringArray(R.array.seat_key);
		String[] seat_value = { seat.getSportseat(), seat.getSeatmaterial(), seat.getSeatheightadjustment(),
				seat.getDriverseatadjustmentmode(), seat.getAuxiliaryseatadjustmentmode(),
				seat.getDriverseatlumbarsupportadjustment(), seat.getDriverseatshouldersupportadjustment(),
				seat.getFrontseatheadrestadjustment(), seat.getRearseatadjustmentmode(),
				seat.getRearseatreclineproportion(), seat.getRearseatangleadjustment(),
				seat.getFrontseatcenterarmrest(), seat.getRearseatcenterarmrest(), seat.getSeatventilation(),
				seat.getSeatheating(), seat.getSeatmassage(), seat.getElectricseatmemory(),
				seat.getChildseatfixdevice(), seat.getThirdrowseat() };

		List<DataModel> seatData = new ArrayList<DataModel>();
		for (int i = 0; i < seat_key.length; i++) {
			seatData.add(new DataModel(seat_key[i], seat_value[i]));
		}

		seatList.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, seatData) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
				helper.setText(R.id.tv_basic_key, item.getKey());
				helper.setText(R.id.tv_basic_value, item.getValue());
			}
		});
	}

	private void setEntcomData(Entcom entcom) {
		String[] entcom_key = getResources().getStringArray(R.array.entcom_key);
		String[] entcom_value = { entcom.getLocationservice(), entcom.getBluetooth(),
				entcom.getExternalaudiointerface(), entcom.getBuiltinharddisk(), entcom.getCartv(),
				entcom.getSpeakernum(), entcom.getAudiobrand(), entcom.getDvd(), entcom.getCd(),
				entcom.getConsolelcdscreen(), entcom.getRearlcdscreen() };

		List<DataModel> entcomData = new ArrayList<DataModel>();
		for (int i = 0; i < entcom_key.length; i++) {
			entcomData.add(new DataModel(entcom_key[i], entcom_value[i]));
		}

		entcomList.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, entcomData) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
				helper.setText(R.id.tv_basic_key, item.getKey());
				helper.setText(R.id.tv_basic_value, item.getValue());
			}
		});
	}

	private void setAircondrefrigeratorData(Aircondrefrigerator air) {
		String[] air_key = getResources().getStringArray(R.array.air_key);
		String[] air_value = { air.getAirconditioningcontrolmode(), air.getTempzonecontrol(),
				air.getRearairconditioning(), air.getReardischargeoutlet(), air.getAirconditioning(),
				air.getAirpurifyingdevice(), air.getCarrefrigerator() };

		List<DataModel> airData = new ArrayList<DataModel>();
		for (int i = 0; i < air_key.length; i++) {
			airData.add(new DataModel(air_key[i], air_value[i]));
		}

		airList.setAdapter(new CommonAdapter<DataModel>(context, R.layout.item_data, airData) {

			@Override
			public void onUpdate(BaseAdapterHelper helper, DataModel item, int position) {
				helper.setText(R.id.tv_basic_key, item.getKey());
				helper.setText(R.id.tv_basic_value, item.getValue());
			}
		});
	}
}
