package com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.okhttp.OkhtttpOfficalDemoActivity;
import com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.okhttplib.RxjavaRetrofitTotalActivity;
import com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.retrofit.RetrofitActivity;
import com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.rxjava.RxjavaActivity;

public class EntryActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_http_request;
    }

    /**
     * mData.add(new ModelRecyclerBean("Retrofit", R.mipmap.circle_captcha, RetrofitActivity.class));
     * mData.add(new ModelRecyclerBean("Rxjava", R.mipmap.circle_captcha, RxjavaActivity.class));
     * mData.add(new ModelRecyclerBean("Rxjava+Retrofit封装", R.mipmap.circle_captcha, RxjavaRetrofitTotalActivity.class));
     * mData.add(new ModelRecyclerBean("Okhttp3", R.mipmap.circle_captcha, Okhttp3Activity.class));
     */
    @Override
    protected void initEvent() {
        findViewById(R.id.btn_rxjava).setOnClickListener(this);
        findViewById(R.id.btn_retrofit).setOnClickListener(this);
        findViewById(R.id.btn_okhttp_offical).setOnClickListener(this);
        findViewById(R.id.btn_rx_retrofit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_okhttp_offical:
                readyGo(OkhtttpOfficalDemoActivity.class);
                break;
            case R.id.btn_retrofit:
                readyGo(RetrofitActivity.class);
                break;
            case R.id.btn_rxjava:
                readyGo(RxjavaActivity.class);
                break;
            case R.id.btn_rx_retrofit:
                readyGo(RxjavaRetrofitTotalActivity.class);
                break;
        }
    }
}
