package com.example.wood121.viewdemos.sdk_thirdparty.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.sdk_thirdparty.map.AddressActivity;

public class MapActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_get_address;
    }

    @Override
    protected void initEvent() {
        findViewById(R.id.tv_getAddress).setOnClickListener(this);
        findViewById(R.id.tv_baidu_api).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getAddress:
                readyGoForResult(AddressActivity.class, 1);
                break;
            case R.id.tv_baidu_api:
                readyGo(BaiduMapActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_OK) {
            if (requestCode == 1) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
