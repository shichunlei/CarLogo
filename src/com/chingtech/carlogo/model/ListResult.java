package com.chingtech.carlogo.model;

import java.util.List;

/**
 * 
 * @Title: ListResult
 * @Description:
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月2日上午10:58:46
 */
public class ListResult {

	private String status;

	private String msg;

	private List<CarBrand> result;

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

	public List<CarBrand> getResult() {
		return result;
	}

	public void setResult(List<CarBrand> result) {
		this.result = result;
	}

}
