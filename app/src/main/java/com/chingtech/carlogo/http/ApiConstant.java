package com.chingtech.carlogo.http;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * Health-Old
 * Package com.chingtech.health.elder.http
 * Description:
 * Created by 师春雷
 * Created at 17/12/8 上午10:06
 */
public class ApiConstant {

    /** GET 获取所有汽车品牌 */
    public final static String CAR_BRAND = "/api/v1/cars/brands";

    /** GET 根据品牌获取车型 */
    public final static String CAR_TYPE = "/api/v1/cars/types";

    /** GET 根据车型获取车 */
    public final static String CAR_CAR = "/api/v1/cars/list";

    /** GET 得到车详情 */
    public final static String CAR_DETAIL = "/api/v1/cars/details";

    /** GET 根据关键字搜索车 */
    public final static String CAR_SEARCH = "/api/v1/cars/search";
}
