package com.example.wood121.viewdemos.apis.threads;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

public class ThreadsActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_threads;
    }

    /**
     * mData.add(new ModelRecyclerBean("AsynTask", R.mipmap.circle_captcha, AsynTaskDemoActivity.class));
     * mData.add(new ModelRecyclerBean("ThreadPoolExecutor", R.mipmap.circle_captcha, ExecutorActivity.class));
     */
    @Override
    protected void initPageViewListener() {
        findViewById(R.id.btn_asyntask).setOnClickListener(this);
        findViewById(R.id.btn_exector).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_asyntask:
                readyGo(AsynTaskDemoActivity.class);
                break;
            case R.id.btn_exector:
                readyGo(ExecutorActivity.class);
                break;
        }
    }
}
