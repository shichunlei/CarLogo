package com.chingtech.carlogo.model;

/**
 * 
 * @Title: Basic
 * @Description: 基本信息
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月2日下午4:58:42
 */
public class Basic {

	/** 厂家指导价 */
	private String price;
	/** 商家报价 */
	private String saleprice;
	/** 保修政策 */
	private String warrantypolicy;
	/** 车船税减免 */
	private String vechiletax;
	/** 排量(L) */
	private String displacement;
	/** 变速箱 */
	private String gearbox;
	/** 综合工况油耗(L/100km) */
	private String comfuelconsumption;
	/** 网友油耗(L/100km) */
	private String userfuelconsumption;
	/** 官方0-100公里加速时间(s) */
	private String officialaccelerationtime100;
	/** 实测0-100公里加速时间(s) */
	private String testaccelerationtime100;
	/** 最高车速(km/h) */
	private String maxspeed;
	/** 乘员人数（区间）(个) */
	private String seatnum;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}

	public String getWarrantypolicy() {
		return warrantypolicy;
	}

	public void setWarrantypolicy(String warrantypolicy) {
		this.warrantypolicy = warrantypolicy;
	}

	public String getVechiletax() {
		return vechiletax;
	}

	public void setVechiletax(String vechiletax) {
		this.vechiletax = vechiletax;
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public String getGearbox() {
		return gearbox;
	}

	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	public String getComfuelconsumption() {
		return comfuelconsumption;
	}

	public void setComfuelconsumption(String comfuelconsumption) {
		this.comfuelconsumption = comfuelconsumption;
	}

	public String getUserfuelconsumption() {
		return userfuelconsumption;
	}

	public void setUserfuelconsumption(String userfuelconsumption) {
		this.userfuelconsumption = userfuelconsumption;
	}

	public String getOfficialaccelerationtime100() {
		return officialaccelerationtime100;
	}

	public void setOfficialaccelerationtime100(String officialaccelerationtime100) {
		this.officialaccelerationtime100 = officialaccelerationtime100;
	}

	public String getTestaccelerationtime100() {
		return testaccelerationtime100;
	}

	public void setTestaccelerationtime100(String testaccelerationtime100) {
		this.testaccelerationtime100 = testaccelerationtime100;
	}

	public String getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(String maxspeed) {
		this.maxspeed = maxspeed;
	}

	public String getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(String seatnum) {
		this.seatnum = seatnum;
	}

}
