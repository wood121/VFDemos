package com.example.wood121.viewdemos.jnis;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

public class JNIDemoActivity extends BaseActivity {

    private TextView tvContent;

    static {
//        System.loadLibrary("myNativeLib");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_jnidemo;
    }

    @Override
    protected void initEvent() {
        tvContent = findViewById(R.id.tv_content);
    }


    private native String getStringFromNative();
}
