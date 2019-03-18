package com.example.wood121.viewdemos.apis;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.loacarepo.utils.RouteConfigs;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

/**
 * 跨module跳转
 */
public class IntentActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_intent;
    }

    @Override
    protected void initPageViewListener() {
        findViewById(R.id.btn_open).setOnClickListener(this);
        findViewById(R.id.btn_action).setOnClickListener(this);
        findViewById(R.id.btn_object).setOnClickListener(this);
        findViewById(R.id.btn_arouter).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
                //localrepo，模块间没有依赖的时候无法识别
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                arouterSkipWood();
                break;
            case R.id.btn_action:
                normalSkip();
                break;
            case R.id.btn_object:
                skip();
                break;
            case R.id.btn_arouter:
                arouterSkip();
                break;
        }
    }

    private void arouterSkipWood() {
        ARouter.getInstance()
                .build(RouteConfigs.WOOD_ACTIVITY)
                .withString("url", "wood121")
                .navigation();
    }

    /**
     * ARouter跳转
     */
    private void arouterSkip() {
        ARouter.getInstance()
                .build(RouteConfigs.SECOND_ACTIVITY)
                .withString("url", "wood121")
                .navigation();
    }

    private void skip() {
        Intent intent = new Intent();
        intent.setClassName("com.example.wood121.viewdemos", "WoodActivity");
        intent.setComponent(new ComponentName("com.example.wood121.viewdemos", "WoodActivity"));
        if (intent.resolveActivity(getPackageManager()) != null) {  //防止隐式调用有人响应
            startActivity(intent);
        }
    }

    /**
     * 一般情况下的跳转，输入的action是不太好找的
     */
    private void normalSkip() {
        Intent intent = new Intent();
        intent.setAction("com.example.loacarepo.WoodActivity"); // 通过intent隐式跳转进行跳转
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }
}
