package com.example.wood121.viewdemos.apis.bitmaps;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

public class BitmapsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_bitmaps;
    }

    @Override
    protected void initPageViewListener() {
        findViewById(R.id.btn_take_photo).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_photo:
                readyGo(TakePhotoActivity.class);
                break;
        }
    }
}
