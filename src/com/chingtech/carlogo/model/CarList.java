package com.chingtech.carlogo.model;

/**
 * 
 * @Title: CarList
 * @Description:
 * @Company: 北京清软时代科技有限公司
 * @author 师春雷
 * @date 2016年8月2日上午12:58:46
 */
public class CarList {

	/** ID */
	private int id;
	/** 名字 */
	private String name;
	/** 首字母 */
	private String initial;
	/** 上级ID */
	private int parentid;
	/** LOGO */
	private String logo;
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
	/** 深度 1品牌 2子公司 3车型 4具体车型 */
	private int depth;

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

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "{\"id\":" + id + ", \"name\":\"" + name + "\", \"initial\":\"" + initial
				+ "\", \"parentid\":" + parentid + ", \"logo\":\"" + logo + "\", \"price\":\"" + price
				+ "\", \"yeartype\":\"" + yeartype + "\", \"productionstate\":\"" + productionstate
				+ "\", \"salestate\":\"" + salestate + "\", \"sizetype\":\"" + sizetype + "\", \"depth\":"
				+ depth + "}";
	}

}
