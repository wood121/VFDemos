package com.example.wood121.viewdemos.net.http.api;

import com.example.wood121.viewdemos.net.http.entity.ApiResult;
import com.example.wood121.viewdemos.net.http.entity.FeedArticleListData;
import com.example.wood121.viewdemos.net.http.retrofit.RetrofitManager;
import com.example.wood121.viewdemos.net.http.utils.RxHelper;

import io.reactivex.Observable;

/**
 * @author wood121
 * @desc
 * @time:2018/8/8
 */
public class MyApi {
    private static MyApi sWoodApi = null;
    private ApiService mApiService;

    private MyApi() {

    }

    private void createApi() {
        mApiService = RetrofitManager.getInstance().getRetrofit().create(ApiService.class);
    }

    public static MyApi getInstance() {
        if (sWoodApi == null) {
            synchronized (MyApi.class) {
                if (sWoodApi == null) {
                    sWoodApi = new MyApi();
                }
            }
        }
        sWoodApi.createApi();
        return sWoodApi;
    }

    public Observable<FeedArticleListData> getFeedArticleList(int num) {
        return mApiService.getFeedArticleList(num)
                .compose(RxHelper.<ApiResult<FeedArticleListData>>io_main())
                .compose(RxHelper.<FeedArticleListData>handleResult());
    }

}
