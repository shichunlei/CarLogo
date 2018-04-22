package com.chingtech.carlogo.http;

import com.chingtech.library.utils.LogUtils;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.chingtech.carlogo.Constant.BASE_URL;

/**
 * Health-Old
 * Package com.chingtech.health.elder.http
 * Description:
 * Created by 师春雷
 * Created at 17/7/29 下午4:06
 */
public class RetrofitManager {

    private Retrofit retrofit;

    private static final int CONNEC_TIMEOUT = 15;
    private static final int READ_TIMEOUT   = 10;
    private static final int WRITE_TIMEOUT  = 10;

    private RxService apiServer;

    private static RetrofitManager mHttpClient;

    public static RetrofitManager getInstance() {
        if (mHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mHttpClient == null) {
                    mHttpClient = new RetrofitManager();
                }
            }
        }
        return mHttpClient;
    }

    private RetrofitManager() {
        // 创建OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                // 超时设置
                .connectTimeout(CONNEC_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);

        // 添加各种插入器
        addInterceptor(builder);

        // 创建Retrofit实例
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                                         .client(builder.build())
                                         .addConverterFactory(GsonConverterFactory.create())
                                         .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                         .build();

        // 创建API接口类
        apiServer = retrofit.create(RxService.class);
    }

    private void addInterceptor(OkHttpClient.Builder builder) {
        // 添加http log
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor(
                message -> LogUtils.i("HttpManager", "[RetrofitManager] " + message));
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logger);
    }

    public RxService getApiService() {
        return apiServer;
    }
}
