package com.example.wood121.viewdemos.frames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;

public class LeakCanaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);

//        ActivityPool.getActivityPool().addActivity(this);

        ToastUtil.showToast(this, "LeakCanaryActivity执行了");
    }


}
