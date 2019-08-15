package com.example.wood121.viewdemos.apis.audio_video;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.apis.bitmaps.BitmapsActivity;
import com.example.wood121.viewdemos.base.BaseActivity;

public class AVActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void initData(Bundle savedInstanceState) {
        @SuppressLint("WrongConstant") WindowManager wm = (WindowManager) getSystemService("window");
        int width = wm.getDefaultDisplay().getWidth();
        int h = wm.getDefaultDisplay().getHeight();

        Log.e("wood121", width + ":" + h);
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_av;
    }

    @Override
    protected void initEvent() {
        findViewById(R.id.btn_video).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_video:
                readyGo(VideoActivity.class);
                break;
            case R.id.btn_take_photo:
                readyGo(BitmapsActivity.class);
                break;
        }
    }
}
