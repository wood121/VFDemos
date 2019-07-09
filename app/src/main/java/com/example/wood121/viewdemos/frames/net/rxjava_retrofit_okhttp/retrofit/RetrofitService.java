package com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.retrofit;

import com.example.wood121.viewdemos.frames.bean.Reception;
import com.example.wood121.viewdemos.frames.bean.Reception2;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by wood121 on 2018/3/19.
 */

public interface RetrofitService {

    /**
     * ***********************get形式***********************
     * url写在注解上
     *
     * @return
     */
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Reception> getCall();

    /**
     * 官方写法
     *
     * @param user
     * @return
     */
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    /**
     * url 单独作为path
     *
     * @param username
     * @return
     */
    @GET("{username}")
    Call<Reception> getUser(@Path("username") String username);

    /**
     * c家使用的url替换形式，可以单独管理url类
     *
     * @param urlPaht
     * @param map
     * @return
     */
    @GET("{path}")
    Call<Reception> getUserBySort(@Path("path") String urlPaht, @QueryMap Map<String, String> map);

    /**
     * **********************post形式***********************
     *
     * @return
     */
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Observable<Reception2> postCall(@Field("i") String targetSentence);

    /**
     * C家的请求方式
     *
     * @param urlPath
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("{path}")
    Call<Reception> postCallMap(@Path("path") String urlPath, @FieldMap Map<String, String> map);
}
