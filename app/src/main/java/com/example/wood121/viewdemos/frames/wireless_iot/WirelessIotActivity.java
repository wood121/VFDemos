package com.example.wood121.viewdemos.frames.wireless_iot;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

/**
 * 无线网络与物联网：wifi、蓝牙、zigbee
 */
public class WirelessIotActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_wireless_iot;
    }

    /**
     * mData.add(new ModelRecyclerBean("蓝牙扫描", R.mipmap.circle_captcha, BlueToothActivity.class));
     * mData.add(new ModelRecyclerBean("udp_chat", R.mipmap.circle_captcha, ChatActivity.class));
     */
    @Override
    protected void initEvent() {
        findViewById(R.id.btn_bt_scan).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bt_scan:
                readyGo(BlueToothActivity.class);
                break;
        }
    }
}
