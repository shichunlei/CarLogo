package com.chingtech.carlogo.model;

import java.util.ArrayList;

/**
 * 
 * @Title: BrandList
 * @Description:
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月2日上午11:58:46
 */
public class BrandList {

	/** ID */
	private int id;
	/** 名称 */
	private String name;
	/** 首字母 */
	private String initial;
	/** 上级ID */
	private int parentid;
	/** LOGO */
	private String logo;
	/** 深度 1品牌 2子公司 3车型 4具体车型 */
	private int depth;
	/** 全名 */
	private String fullname;
	/** 销售状态 */
	private String salestate;
	/** 具体车列表 */
	private ArrayList<CarList> list;

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

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSalestate() {
		return salestate;
	}

	public void setSalestate(String salestate) {
		this.salestate = salestate;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public ArrayList<CarList> getList() {
		return list;
	}

	public void setList(ArrayList<CarList> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "{\"list\":" + list + "}";
	}

}
