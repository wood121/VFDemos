package com.example.wood121.viewdemos.net.http.retrofit;

import com.example.wood121.viewdemos.net.http.apis.HttpConfig;
import com.example.wood121.viewdemos.net.http.okhttp.InceptorManager;
import com.example.wood121.viewdemos.net.http.okhttp.Wood121LoggerInceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wood121
 * @desc
 * @time:2018/8/8
 */
public class RetrofitManager {
    private static RetrofitManager sRetrofitManager;
    private final Retrofit mRetrofit;

    private RetrofitManager() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new Wood121LoggerInceptor())
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetrofitManager getInstance() {
        if (sRetrofitManager == null) {
            synchronized (InceptorManager.class) {
                if (sRetrofitManager == null) {
                    sRetrofitManager = new RetrofitManager();
                }
            }
        }
        return sRetrofitManager;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
