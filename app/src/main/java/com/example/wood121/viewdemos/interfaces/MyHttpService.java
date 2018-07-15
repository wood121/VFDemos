package com.example.wood121.viewdemos.interfaces;

import com.example.wood121.viewdemos.bean.Reception;
import com.example.wood121.viewdemos.bean.Reception2;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by wood121 on 2018/3/19.
 */

public interface MyHttpService {

    /**
     * 注解中传入网络请求的 部分URL
     *
     * @return
     */
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Reception> getCall();

    /**
     * //采用@Post表示Post方法进行请求（传入部分url地址）
     * // 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
     * // 需要配合@Field 向服务器提交需要的字段
     *
     * @return
     */
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Observable<Reception2> postCall(@Field("i") String targetSentence);

}
