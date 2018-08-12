package com.example.wood121.viewdemos.net.http.apis;

import com.example.wood121.viewdemos.net.http.retrofit.RetrofitManager;

/**
 * @author wood121
 * @desc
 * @time:2018/8/8
 */
public class WoodApi {
    private static WoodApi sWoodApi = null;
    private ApiService mApiService;

    private WoodApi() {

    }

    private void createApi() {
        mApiService = RetrofitManager.getInstance().getRetrofit().create(ApiService.class);
    }

    public static WoodApi getInstance() {
        if (sWoodApi == null) {
            synchronized (WoodApi.class) {
                if (sWoodApi == null) {
                    sWoodApi = new WoodApi();
                }
            }
        }
        sWoodApi.createApi();
        return sWoodApi;
    }


}
