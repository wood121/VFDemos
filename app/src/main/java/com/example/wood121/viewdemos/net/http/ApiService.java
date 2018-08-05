package com.example.wood121.viewdemos.net.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wood121 on 2018/7/17.
 */

public interface ApiService {

    @GET("/student/login")
    Observable<HttpResult> getTopMovie(@Query("start") int start, @Query("count") int count);
}
