package com.chingtech.carlogo.model;

/**
 * 
 * @Title: Actualtest
 * @Description: 实际测试
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月2日下午5:00:26
 */
public class Actualtest {

	/** 加速时间（0—100km/h）(s) */
	private String accelerationtime100;
	/** 制动距离（100—0km/h）(m) */
	private String brakingdistance;

	public String getAccelerationtime100() {
		return accelerationtime100;
	}

	public void setAccelerationtime100(String accelerationtime100) {
		this.accelerationtime100 = accelerationtime100;
	}

	public String getBrakingdistance() {
		return brakingdistance;
	}

	public void setBrakingdistance(String brakingdistance) {
		this.brakingdistance = brakingdistance;
	}

}
