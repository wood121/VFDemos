package com.example.wood121.viewdemos.frame_part;

import com.example.wood121.viewdemos.frame_part.bean.Reception;
import com.example.wood121.viewdemos.frame_part.bean.Reception2;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wood121 on 2018/3/19.
 */

public interface MyHttpService {

    /**
     * ***********************get形式***********************
     *
     * @return
     */
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Reception> getCall();

    @GET("{username}")
    Call<Reception> getUser(@Path("username") String username);

    @GET("user")
    Call<Reception> getUserBySort(@Query("sortBy") String sort);

    /**
     * **********************post形式***********************
     *
     * @return
     */
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Observable<Reception2> postCall(@Field("i") String targetSentence);

}
