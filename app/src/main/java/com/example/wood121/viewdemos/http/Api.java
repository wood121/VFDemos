package com.example.wood121.viewdemos.http;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wood121 on 2018/7/17.
 * 初始化Retrofit
 */

public class Api {
    private static ApiService SERVICE;
    private static final int DEFAULT_TIMEOUT = 10000;

    public static ApiService getDefault() {
        if (SERVICE == null) {
            SERVICE = new Retrofit.Builder()
                    .client(getMyOkhttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("http://grayconf.i2863.com")
                    .build()
                    .create(ApiService.class);
        }

        return SERVICE;
    }

    /**
     * 添加了okHttp3.0一些配置
     *
     * @return
     */
    @NonNull
    private static OkHttpClient getMyOkhttpClient() {
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(getTokenInterceptor())
                .authenticator(getAuthenticator())
                .build();

        return okHttpClient;
    }

    private static Authenticator getAuthenticator() {
        return new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {

//                response.request().newBuilder().addHeader("Authorization",)

                return null;
            }
        };
    }

    private static Interceptor getTokenInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                UserBean userBean = new UserBean();
                //是空的，比如一开始还没有token的情况
                if (userBean.getToken() == null) {
                    return chain.proceed(originalRequest);
                }

                //如果过期了，刷新下token，request更换为新的请求队列
                Request authorizationQuest = originalRequest.newBuilder()
                        .header("Authorization", userBean.getToken())
                        .build();
                return chain.proceed(authorizationQuest);
            }
        };
    }


}
