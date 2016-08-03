package com.chingtech.carlogo.utils;

public class ApiUtil {

	public final static String ROOT_URL = "http://api.jisuapi.com";

	public static final int TIME_OUT = 10 * 1000;

	/** GET 获取所有汽车品牌 */
	public final static String CAR_BRAND = "/car/brand";

	/** GET 获取所有车型 */
	public final static String CAR_MODEL_LIST = "/car/carlist";

	/** GET 车型详情 */
	public final static String CAR_DETAIL = "/car/detail";
}
