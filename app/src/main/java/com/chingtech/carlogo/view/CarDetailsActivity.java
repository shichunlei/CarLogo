package com.chingtech.carlogo.view;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.chingtech.carlogo.R;
import com.chingtech.carlogo.adapter.CarDetailsAdapter;
import com.chingtech.carlogo.http.RetrofitManager;
import com.chingtech.carlogo.model.*;
import com.chingtech.library.base.activity.BaseActivity;
import com.chingtech.library.utils.LogUtils;
import com.chingtech.library.utils.StringUtils;
import com.chingtech.library.utils.ViewUtils;
import com.chingtech.library.widget.RecycleViewDivider;
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
 * Created at 18/4/14 下午1:59
 */
public class CarDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.iv_logo)
    ImageView logo;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout layout;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private List<CarInfoSection> list = new ArrayList<>();

    private int model_id;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar).init();
        String title = getStringExtra("title");
        initToolBar(toolbar, tvTitle, true, title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_details;
    }

    @Override
    protected void initView() {
        model_id = getIntExtra("model_id");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(
                new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, 2,
                                       R.color.line_color));
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        RetrofitManager.getInstance()
                       .getApiService()
                       .carDetails(JISHU_APPKEY, model_id)
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .compose(this.bindToLifecycle())
                       .subscribe(new Observer<BaseResult<CarBean>>() {
                           @Override
                           public void onSubscribe(Disposable d) {
                           }

                           @Override
                           public void onNext(BaseResult<CarBean> bean) {
                               if (bean.success()) {
                                   LogUtils.i("TAG", bean.getResult().toString());

                                   ViewUtils.initImage(context, logo, bean.getResult().getLogo(),
                                                       R.drawable.icon_loading);

                                   setData(bean.getResult());
                               }
                           }

                           @Override
                           public void onError(Throwable e) {
                               mStateView.showRetry();
                               LogUtils.d("TAG", "onError----" + e.getMessage());
                           }

                           @Override
                           public void onComplete() {
                               mStateView.hidenLoading();
                           }
                       });
    }

    private void setData(CarBean result) {
        list.add(new CarInfoSection(true, "基本信息"));
        List<CarInfo> basic = getBasicData(result.getBasic());
        for (int i = 0; i < basic.size(); i++) {
            list.add(new CarInfoSection(basic.get(i)));
        }

        list.add(new CarInfoSection(true, "车体信息"));
        List<CarInfo> body = getBodyData(result.getBody());
        for (int i = 0; i < body.size(); i++) {
            list.add(new CarInfoSection(body.get(i)));
        }

        list.add(new CarInfoSection(true, "发动机"));
        List<CarInfo> engine = getEngineData(result.getEngine());
        for (int i = 0; i < engine.size(); i++) {
            list.add(new CarInfoSection(engine.get(i)));
        }

        list.add(new CarInfoSection(true, "变速箱"));
        List<CarInfo> box = getBoxData(result.getGearbox());
        for (int i = 0; i < box.size(); i++) {
            list.add(new CarInfoSection(box.get(i)));
        }

        list.add(new CarInfoSection(true, "换挡拨片"));
        List<CarInfo> chassisbrake = getChassisbrakeData(result.getChassisbrake());
        for (int i = 0; i < chassisbrake.size(); i++) {
            list.add(new CarInfoSection(chassisbrake.get(i)));
        }

        list.add(new CarInfoSection(true, "安全配置"));
        List<CarInfo> safe = getSafeData(result.getSafe());
        for (int i = 0; i < safe.size(); i++) {
            list.add(new CarInfoSection(safe.get(i)));
        }

        list.add(new CarInfoSection(true, "车轮配置"));
        List<CarInfo> wheel = getWheelData(result.getWheel());
        for (int i = 0; i < wheel.size(); i++) {
            list.add(new CarInfoSection(wheel.get(i)));
        }

        list.add(new CarInfoSection(true, "行车辅助"));
        List<CarInfo> drivingauxiliary = getDrivingauxiliaryData(result.getDrivingauxiliary());
        for (int i = 0; i < drivingauxiliary.size(); i++) {
            list.add(new CarInfoSection(drivingauxiliary.get(i)));
        }

        list.add(new CarInfoSection(true, "门窗/后视镜"));
        List<CarInfo> doormirror = getDoormirrorData(result.getDoormirror());
        for (int i = 0; i < doormirror.size(); i++) {
            list.add(new CarInfoSection(doormirror.get(i)));
        }

        list.add(new CarInfoSection(true, "灯光配置"));
        List<CarInfo> light = getLightData(result.getLight());
        for (int i = 0; i < light.size(); i++) {
            list.add(new CarInfoSection(light.get(i)));
        }

        list.add(new CarInfoSection(true, "内部配置"));
        List<CarInfo> internalconfig = getInternalconfigData(result.getInternalconfig());
        for (int i = 0; i < internalconfig.size(); i++) {
            list.add(new CarInfoSection(internalconfig.get(i)));
        }

        list.add(new CarInfoSection(true, "座椅配置"));
        List<CarInfo> seat = getSeatData(result.getSeat());
        for (int i = 0; i < seat.size(); i++) {
            list.add(new CarInfoSection(seat.get(i)));
        }

        list.add(new CarInfoSection(true, "娱乐配置"));
        List<CarInfo> entcom = getEntcomData(result.getEntcom());
        for (int i = 0; i < entcom.size(); i++) {
            list.add(new CarInfoSection(entcom.get(i)));
        }

        list.add(new CarInfoSection(true, "空调/冰箱"));
        List<CarInfo> air = getAirData(result.getAircondrefrigerator());
        for (int i = 0; i < air.size(); i++) {
            list.add(new CarInfoSection(air.get(i)));
        }

        list.add(new CarInfoSection(true, "实际测试"));
        List<CarInfo> test = getTestData(result.getActualtest());
        for (int i = 0; i < test.size(); i++) {
            list.add(new CarInfoSection(test.get(i)));
        }

        CarDetailsAdapter adapter = new CarDetailsAdapter(R.layout.item_info,
                                                          R.layout.item_type_header, list);
        recyclerview.setAdapter(adapter);
    }

    private List<CarInfo> getBasicData(Basic basic) {
        String[] basic_key = getResources().getStringArray(R.array.basic_key);
        String[] basic_value = {basic.getPrice(), basic.getSaleprice(), basic.getWarrantypolicy(),
                basic.getVechiletax(), basic.getDisplacement(), basic.getGearbox(),
                basic.getComfuelconsumption(), basic.getUserfuelconsumption(),
                basic.getOfficialaccelerationtime100(), basic.getTestaccelerationtime100(),
                basic.getMaxspeed(), basic.getSeatnum()};

        List<CarInfo> basicData = new ArrayList<>();
        for (int i = 0; i < basic_key.length; i++) {
            basicData.add(new CarInfo(basic_key[i], basic_value[i]));
        }

        return basicData;
    }

    private List<CarInfo> getBodyData(Body body) {
        String[] body_key = getResources().getStringArray(R.array.body_key);
        String[] body_value = {StringUtils.isEmpty(body.getLen()) ? "" :
                body.getLen() + "*" + body.getWidth() + "*" + body.getHeight(), body.getWheelbase(),
                body.getFronttrack(), body.getReartrack(), body.getWeight(), body.getFullweight(),
                body.getMingroundclearance(), body.getApproachangle(), body.getDepartureangle(),
                body.getLuggagevolume(), body.getLuggagemode(), body.getLuggageopenmode(),
                body.getInductionluggage(), body.getDoornum(), body.getTooftype(),
                body.getHoodtype(), body.getRoofluggagerack(), body.getSportpackage()};

        List<CarInfo> bodyData = new ArrayList<>();
        for (int i = 0; i < body_key.length; i++) {
            bodyData.add(new CarInfo(body_key[i], body_value[i]));
        }
        return bodyData;
    }

    private List<CarInfo> getEngineData(Engine engine) {
        String[] engine_key = getResources().getStringArray(R.array.engine_key);

        String[] engine_value = {engine.getPosition(), engine.getModel(), engine.getDisplacement(),
                engine.getDisplacementml(), engine.getIntakeform(), engine.getCylinderarrangetype(),
                engine.getCylindernum(), engine.getValvetrain(), engine.getValvestructure(),
                engine.getCompressionratio(), engine.getBore(), engine.getStroke(),
                engine.getMaxhorsepower(), engine.getMaxpower(), engine.getMaxpowerspeed(),
                engine.getMaxtorque(), engine.getMaxtorquespeed(), engine.getFueltype(),
                engine.getFuelgrade(), engine.getFuelmethod(), engine.getFueltankcapacity(),
                engine.getCylinderheadmaterial(), engine.getCylinderbodymaterial(),
                engine.getEnvironmentalstandards(), engine.getStartstopsystem()};

        List<CarInfo> engineData = new ArrayList<>();
        for (int i = 0; i < engine_key.length; i++) {
            engineData.add(new CarInfo(engine_key[i], engine_value[i]));
        }
        return engineData;
    }

    private List<CarInfo> getBoxData(Gearbox gearbox) {
        String[] box_key = getResources().getStringArray(R.array.gearbox_key);

        String[] box_value = {gearbox.getGearbox(), gearbox.getShiftpaddles()};

        List<CarInfo> data = new ArrayList<>();
        for (int i = 0; i < box_key.length; i++) {
            data.add(new CarInfo(box_key[i], box_value[i]));
        }
        return data;
    }

    private List<CarInfo> getChassisbrakeData(Chassisbrake chassisbrake) {
        String[] chassisbrake_key = getResources().getStringArray(R.array.chassisbrake_key);
        String[] chassisbrake_value = {chassisbrake.getBodystructure(),
                chassisbrake.getPowersteering(), chassisbrake.getFrontbraketype(),
                chassisbrake.getRearbraketype(), chassisbrake.getParkingbraketype(),
                chassisbrake.getDrivemode(), chassisbrake.getAirsuspension(),
                chassisbrake.getAdjustablesuspension(), chassisbrake.getFrontsuspensiontype(),
                chassisbrake.getRearsuspensiontype(), chassisbrake.getCenterdifferentiallock()};

        List<CarInfo> chassisbrakeData = new ArrayList<>();
        for (int i = 0; i < chassisbrake_key.length; i++) {
            chassisbrakeData.add(new CarInfo(chassisbrake_key[i], chassisbrake_value[i]));
        }
        return chassisbrakeData;
    }

    private List<CarInfo> getSafeData(Safe safe) {
        String[] safe_key = getResources().getStringArray(R.array.safe_key);
        String[] safe_value = {safe.getAirbagdrivingposition(), safe.getAirbagfrontpassenger(),
                safe.getAirbagfrontside(), safe.getAirbagfronthead(), safe.getAirbagknee(),
                safe.getAirbagrearside(), safe.getAirbagrearhead(), safe.getSafetybeltprompt(),
                safe.getSafetybeltlimiting(), safe.getSafetybeltpretightening(),
                safe.getFrontsafetybeltadjustment(), safe.getRearsafetybelt(),
                safe.getTirepressuremonitoring(), safe.getZeropressurecontinued(),
                safe.getCentrallocking(), safe.getChildlock(), safe.getRemotekey(),
                safe.getKeylessentry(), safe.getKeylessstart(), safe.getEngineantitheft()};

        List<CarInfo> safeData = new ArrayList<>();
        for (int i = 0; i < safe_key.length; i++) {
            safeData.add(new CarInfo(safe_key[i], safe_value[i]));
        }

        return safeData;
    }

    private List<CarInfo> getWheelData(Wheel wheel) {
        String[] wheel_key = getResources().getStringArray(R.array.wheel_key);
        String[] wheel_value = {wheel.getFronttiresize(), wheel.getReartiresize(),
                wheel.getSparetiretype(), wheel.getHubmaterial()};

        List<CarInfo> data = new ArrayList<>();
        for (int i = 0; i < wheel_key.length; i++) {
            data.add(new CarInfo(wheel_key[i], wheel_value[i]));
        }

        return data;
    }

    private List<CarInfo> getDrivingauxiliaryData(Drivingauxiliary driving) {
        String[] driving_key = getResources().getStringArray(R.array.driving_key);
        String[] driving_value = {driving.getAbs(), driving.getEbd(), driving.getBrakeassist(),
                driving.getTractioncontrol(), driving.getEsp(), driving.getEps(),
                driving.getAutomaticparking(), driving.getHillstartassist(),
                driving.getHilldescent(), driving.getFrontparkingradar(),
                driving.getReversingradar(), driving.getReverseimage(),
                driving.getPanoramiccamera(), driving.getCruisecontrol(),
                driving.getAdaptivecruise(), driving.getGps(),
                driving.getAutomaticparkingintoplace(), driving.getLdws(),
                driving.getActivebraking(), driving.getIntegralactivesteering(),
                driving.getNightvisionsystem(), driving.getBlindspotdetection()};

        List<CarInfo> drivingData = new ArrayList<>();
        for (int i = 0; i < driving_key.length; i++) {
            drivingData.add(new CarInfo(driving_key[i], driving_value[i]));
        }
        return drivingData;
    }

    private List<CarInfo> getDoormirrorData(Doormirror door) {
        String[] door_key = getResources().getStringArray(R.array.door_key);
        String[] door_value = {door.getOpenstyle(), door.getElectricwindow(),
                door.getUvinterceptingglass(), door.getPrivacyglass(), door.getAntipinchwindow(),
                door.getSkylightopeningmode(), door.getSkylightstype(),
                door.getRearwindowsunshade(), door.getRearsidesunshade(), door.getRearwiper(),
                door.getSensingwiper(), door.getElectricpulldoor(),
                door.getRearmirrorwithturnlamp(), door.getExternalmirrormemory(),
                door.getExternalmirrorheating(), door.getExternalmirrorfolding(),
                door.getExternalmirroradjustment(), door.getRearviewmirrorantiglare(),
                door.getSunvisormirror()};

        List<CarInfo> doorData = new ArrayList<>();
        for (int i = 0; i < door_key.length; i++) {
            doorData.add(new CarInfo(door_key[i], door_value[i]));
        }

        return doorData;
    }

    private List<CarInfo> getLightData(Light light) {
        String[] light_key = getResources().getStringArray(R.array.light_key);
        String[] light_value = {light.getHeadlighttype(), light.getOptionalheadlighttype(),
                light.getHeadlightautomaticopen(), light.getHeadlightautomaticclean(),
                light.getHeadlightdelayoff(), light.getHeadlightdynamicsteering(),
                light.getHeadlightilluminationadjustment(), light.getHeadlightdimming(),
                light.getFrontfoglight(), light.getReadinglight(), light.getInteriorairlight(),
                light.getDaytimerunninglight(), light.getLedtaillight(),
                light.getLightsteeringassist()};

        List<CarInfo> lightData = new ArrayList<>();
        for (int i = 0; i < light_key.length; i++) {
            lightData.add(new CarInfo(light_key[i], light_value[i]));
        }
        return lightData;
    }

    private List<CarInfo> getInternalconfigData(Internalconfig internal) {
        String[] internal_key = getResources().getStringArray(R.array.internal_key);
        String[] internal_value = {internal.getSteeringwheelbeforeadjustment(),
                internal.getSteeringwheelupadjustment(), internal.getSteeringwheeladjustmentmode(),
                internal.getSteeringwheelmemory(), internal.getSteeringwheelmaterial(),
                internal.getSteeringwheelmultifunction(), internal.getSteeringwheelheating(),
                internal.getComputerscreen(), internal.getHuddisplay(), internal.getInteriorcolor(),
                internal.getRearcupholder(), internal.getSupplyvoltage()};

        List<CarInfo> internalData = new ArrayList<>();
        for (int i = 0; i < internal_key.length; i++) {
            internalData.add(new CarInfo(internal_key[i], internal_value[i]));
        }
        return internalData;
    }

    private List<CarInfo> getSeatData(Seat seat) {
        String[] seat_key = getResources().getStringArray(R.array.seat_key);
        String[] seat_value = {seat.getSportseat(), seat.getSeatmaterial(),
                seat.getSeatheightadjustment(), seat.getDriverseatadjustmentmode(),
                seat.getAuxiliaryseatadjustmentmode(), seat.getDriverseatlumbarsupportadjustment(),
                seat.getDriverseatshouldersupportadjustment(),
                seat.getFrontseatheadrestadjustment(), seat.getRearseatadjustmentmode(),
                seat.getRearseatreclineproportion(), seat.getRearseatangleadjustment(),
                seat.getFrontseatcenterarmrest(), seat.getRearseatcenterarmrest(),
                seat.getSeatventilation(), seat.getSeatheating(), seat.getSeatmassage(),
                seat.getElectricseatmemory(), seat.getChildseatfixdevice(), seat.getThirdrowseat()};

        List<CarInfo> seatData = new ArrayList<>();
        for (int i = 0; i < seat_key.length; i++) {
            seatData.add(new CarInfo(seat_key[i], seat_value[i]));
        }
        return seatData;
    }

    private List<CarInfo> getEntcomData(Entcom entcom) {
        String[] entcom_key = getResources().getStringArray(R.array.entcom_key);
        String[] entcom_value = {entcom.getLocationservice(), entcom.getBluetooth(),
                entcom.getExternalaudiointerface(), entcom.getBuiltinharddisk(), entcom.getCartv(),
                entcom.getSpeakernum(), entcom.getAudiobrand(), entcom.getDvd(), entcom.getCd(),
                entcom.getConsolelcdscreen(), entcom.getRearlcdscreen()};

        List<CarInfo> entcomData = new ArrayList<>();
        for (int i = 0; i < entcom_key.length; i++) {
            entcomData.add(new CarInfo(entcom_key[i], entcom_value[i]));
        }
        return entcomData;
    }

    private List<CarInfo> getAirData(Aircondrefrigerator air) {
        String[] air_key = getResources().getStringArray(R.array.air_key);
        String[] air_value = {air.getAirconditioningcontrolmode(), air.getTempzonecontrol(),
                air.getRearairconditioning(), air.getReardischargeoutlet(),
                air.getAirconditioning(), air.getAirpurifyingdevice(), air.getCarrefrigerator()};

        List<CarInfo> airData = new ArrayList<>();
        for (int i = 0; i < air_key.length; i++) {
            airData.add(new CarInfo(air_key[i], air_value[i]));
        }
        return airData;
    }

    private List<CarInfo> getTestData(Actualtest test) {
        String[] test_key   = getResources().getStringArray(R.array.actualtest_key);
        String[] test_value = {test.getAccelerationtime100(), test.getBrakingdistance()};

        List<CarInfo> testData = new ArrayList<>();
        for (int i = 0; i < test_key.length; i++) {
            testData.add(new CarInfo(test_key[i], test_value[i]));
        }
        return testData;
    }

    @Override
    protected View injectTarget() {
        return layout;
    }
}
