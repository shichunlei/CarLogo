package com.chingtech.carlogo.model;

/**
 * 
 * <p>
 * Title: Wheel
 * </p>
 * <p>
 * Description: 车轮
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author 师春雷
 * @date 2016年8月2日下午4:54:34
 */
public class Wheel {

	/** 前轮胎规格 */
	private String fronttiresize;
	/** 后轮胎规格 */
	private String reartiresize;
	/** 备胎类型 */
	private String sparetiretype;
	/** 轮毂材料 */
	private String hubmaterial;

	public String getFronttiresize() {
		return fronttiresize;
	}

	public void setFronttiresize(String fronttiresize) {
		this.fronttiresize = fronttiresize;
	}

	public String getReartiresize() {
		return reartiresize;
	}

	public void setReartiresize(String reartiresize) {
		this.reartiresize = reartiresize;
	}

	public String getSparetiretype() {
		return sparetiretype;
	}

	public void setSparetiretype(String sparetiretype) {
		this.sparetiretype = sparetiretype;
	}

	public String getHubmaterial() {
		return hubmaterial;
	}

	public void setHubmaterial(String hubmaterial) {
		this.hubmaterial = hubmaterial;
	}

}
