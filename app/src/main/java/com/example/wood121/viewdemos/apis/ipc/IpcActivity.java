package com.example.wood121.viewdemos.apis.ipc;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

public class IpcActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_ipc;
    }

    /**
     * mData.add(new ModelRecyclerBean("messenger", R.mipmap.circle_captcha, MessengerActivity.class));
     */
    @Override
    protected void initEvent() {
        findViewById(R.id.btn_messager).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_messager:
                
                break;
        }
    }
}
