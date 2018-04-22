package com.chingtech.carlogo.http;

import com.chingtech.carlogo.model.BaseResult;
import com.chingtech.carlogo.model.CarBean;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Health-Old
 * Package com.chingtech.health.elder.http
 * Description:
 * Created by 师春雷
 * Created at 17/7/29 下午3:35
 */
public interface RxService {

    /**
     * 获取所有品牌
     *
     * @param appkey your appkey
     * @return
     */
    @GET(ApiConstant.CAR_BRAND)
    Observable<BaseResult<List<CarBean>>> allCarBrand(@Query("appkey") String appkey);

    /**
     * 根据品牌获取车型
     *
     * @param appkey   your appkey
     * @param brand_id 上级ID
     * @return
     */
    @GET(ApiConstant.CAR_TYPE)
    Observable<BaseResult<List<CarBean>>> carType(@Query("appkey") String appkey,
            @Query("brand_id") int brand_id);

    /**
     * 根据品牌获取车
     *
     * @param appkey  your appkey
     * @param type_id 上级ID
     * @return
     */
    @GET(ApiConstant.CAR_CAR)
    Observable<BaseResult<List<CarBean>>> carModel(@Query("appkey") String appkey,
            @Query("type_id") int type_id);

    /**
     * 获取车型详情
     *
     * @param appkey   your appkey
     * @param model_id 车ID
     * @return
     */
    @GET(ApiConstant.CAR_DETAIL)
    Observable<BaseResult<CarBean>> carDetails(@Query("appkey") String appkey,
            @Query("model_id") int model_id);

    /**
     * 车型搜索
     *
     * @param appkey  your appkey
     * @param keyword 搜索关键字
     * @return
     */
    @GET(ApiConstant.CAR_SEARCH)
    Observable<BaseResult<List<CarBean>>> carSearch(@Query("appkey") String appkey,
            @Query("keyword") String keyword);
}
