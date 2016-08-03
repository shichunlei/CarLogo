package com.chingtech.carlogo.model;

/**
 * 
 * @Title: Result
 * @Description:
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月2日上午10:56:46
 */
public class Result {

	private String status;

	private String msg;

	private CarBrand result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CarBrand getResult() {
		return result;
	}

	public void setResult(CarBrand result) {
		this.result = result;
	}

}
