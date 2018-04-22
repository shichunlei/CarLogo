package com.chingtech.carlogo.model;

/**
 * @author 师春雷
 * @Title: Wheel
 * @Description: 车轮
 * @Company: 北京清软时代科技有限公司
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
