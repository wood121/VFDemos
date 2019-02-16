package com.example.wood121.viewdemos.apis.database_;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.apis.database_.sqlite_book.SqliteActivity;
import com.example.wood121.viewdemos.apis.database_.sqlite_device.SqliteDeviceActivity;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.Okhttp3Activity;
import com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.RetrofitActivity;
import com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.RxjavaActivity;
import com.example.wood121.viewdemos.views.charts.ZheActivity;

public class DBActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_db;
    }

    /**
     * mData.add(new ModelRecyclerBean("TableBook_DBManager", R.mipmap.circle_captcha, SqliteActivity.class));
     * mData.add(new ModelRecyclerBean("TableVirtualidDeviceBean_DBManager", R.mipmap.circle_captcha, SqliteDeviceActivity.class));
     */
    @Override
    protected void initPageViewListener() {
        findViewById(R.id.btn_sq_book).setOnClickListener(this);
        findViewById(R.id.btn_sq_device).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sq_book:
                readyGo(SqliteActivity.class);
                break;
            case R.id.btn_sq_device:
                readyGo(SqliteDeviceActivity.class);
                break;
        }
    }
}
