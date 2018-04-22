package com.chingtech.carlogo.model;

import java.util.ArrayList;

/**
 * @author 师春雷
 * @Title: CarBean
 * @Description:
 * @Company: 北京清软时代科技有限公司
 * @date 2016年8月2日上午10:57:50
 */
public class CarBean {

    /** ID */
    private int    id;
    /** 名称 */
    private String name;
    /** 全称 */
    private String fullname;
    /** 首字母 */
    private String initial;
    /** 上级名称 */
    private String parentname;
    /** LOGO */
    private String logo;
    /** 深度 1品牌 2子公司 3车型 4具体车型 */
    private int    depth;

    /** 车型列表 */
    private ArrayList<CarBean> list;

    /** 官方指导价 */
    private String price;
    /** 年款 */
    private String yeartype;
    /** 生产状态 */
    private String productionstate;
    /** 销售状态 */
    private String salestate;
    /** 尺寸类型 */
    private String sizetype;

    /** 基本信息 */
    private Basic               basic;
    /** 车体 */
    private Body                body;
    /** 发动机 */
    private Engine              engine;
    /** 变速箱 */
    private Gearbox             gearbox;
    /** 底盘制动 */
    private Chassisbrake        chassisbrake;
    /** 安全配置 */
    private Safe                safe;
    /** 车轮 */
    private Wheel               wheel;
    /** 行车辅助 */
    private Drivingauxiliary    drivingauxiliary;
    /** 门窗/后视镜 */
    private Doormirror          doormirror;
    /** 灯光 */
    private Light               light;
    /** 内部配置 */
    private Internalconfig      internalconfig;
    /** 座椅 */
    private Seat                seat;
    /** 娱乐通讯 */
    private Entcom              entcom;
    /** 空调/冰箱 */
    private Aircondrefrigerator aircondrefrigerator;
    /** 实际测试 */
    private Actualtest          actualtest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public ArrayList<CarBean> getList() {
        return list;
    }

    public void setList(ArrayList<CarBean> list) {
        this.list = list;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getYeartype() {
        return yeartype;
    }

    public void setYeartype(String yeartype) {
        this.yeartype = yeartype;
    }

    public String getProductionstate() {
        return productionstate;
    }

    public void setProductionstate(String productionstate) {
        this.productionstate = productionstate;
    }

    public String getSalestate() {
        return salestate;
    }

    public void setSalestate(String salestate) {
        this.salestate = salestate;
    }

    public String getSizetype() {
        return sizetype;
    }

    public void setSizetype(String sizetype) {
        this.sizetype = sizetype;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public Chassisbrake getChassisbrake() {
        return chassisbrake;
    }

    public void setChassisbrake(Chassisbrake chassisbrake) {
        this.chassisbrake = chassisbrake;
    }

    public Safe getSafe() {
        return safe;
    }

    public void setSafe(Safe safe) {
        this.safe = safe;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Drivingauxiliary getDrivingauxiliary() {
        return drivingauxiliary;
    }

    public void setDrivingauxiliary(Drivingauxiliary drivingauxiliary) {
        this.drivingauxiliary = drivingauxiliary;
    }

    public Doormirror getDoormirror() {
        return doormirror;
    }

    public void setDoormirror(Doormirror doormirror) {
        this.doormirror = doormirror;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public Internalconfig getInternalconfig() {
        return internalconfig;
    }

    public void setInternalconfig(Internalconfig internalconfig) {
        this.internalconfig = internalconfig;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Entcom getEntcom() {
        return entcom;
    }

    public void setEntcom(Entcom entcom) {
        this.entcom = entcom;
    }

    public Aircondrefrigerator getAircondrefrigerator() {
        return aircondrefrigerator;
    }

    public void setAircondrefrigerator(Aircondrefrigerator aircondrefrigerator) {
        this.aircondrefrigerator = aircondrefrigerator;
    }

    public Actualtest getActualtest() {
        return actualtest;
    }

    public void setActualtest(Actualtest actualtest) {
        this.actualtest = actualtest;
    }

    @Override
    public String toString() {
        return "CarBean{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", fullname='"
                + fullname
                + '\''
                + ", initial='"
                + initial
                + '\''
                + ", parentname='"
                + parentname
                + "\', logo='"
                + logo
                + '\''
                + ", depth="
                + depth
                + ", list="
                + list
                + ", price='"
                + price
                + '\''
                + ", yeartype='"
                + yeartype
                + '\''
                + ", productionstate='"
                + productionstate
                + '\''
                + ", salestate='"
                + salestate
                + '\''
                + ", sizetype='"
                + sizetype
                + '\''
                + ", basic="
                + basic
                + ", body="
                + body
                + ", engine="
                + engine
                + ", gearbox="
                + gearbox
                + ", chassisbrake="
                + chassisbrake
                + ", safe="
                + safe
                + ", wheel="
                + wheel
                + ", drivingauxiliary="
                + drivingauxiliary
                + ", doormirror="
                + doormirror
                + ", light="
                + light
                + ", internalconfig="
                + internalconfig
                + ", seat="
                + seat
                + ", entcom="
                + entcom
                + ", aircondrefrigerator="
                + aircondrefrigerator
                + ", actualtest="
                + actualtest
                + '}';
    }
}
