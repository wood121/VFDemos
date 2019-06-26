package com.example.wood121.viewdemos.views.widgets_self;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.util.ToastUtil;
import com.example.wood121.viewdemos.views.widgets_self.base.View3StepActivity;
import com.example.wood121.viewdemos.views.widgets_self.circlecheck.CheckProcessActivity;
import com.example.wood121.viewdemos.views.widgets_self.danmu.DanMuActivity;
import com.example.wood121.viewdemos.views.widgets_self.topbar.TopbarActivity;

/**
 * 自定义控件组织
 */
public class WidgetsSelfActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_widgetsself;
    }

    @Override
    protected void initEvent() {
        findViewById(R.id.btn_arcview).setOnClickListener(this);
        findViewById(R.id.btn_checkprocess).setOnClickListener(this);
        findViewById(R.id.btn_topbar).setOnClickListener(this);
        findViewById(R.id.btn_danmu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_arcview:  //自定义三部曲，博客练习
                readyGo(View3StepActivity.class);
                break;
            case R.id.btn_checkprocess:
                readyGo(CheckProcessActivity.class);
                break;
            case R.id.btn_topbar:   //标题栏
                readyGo(TopbarActivity.class);
                break;
            case R.id.btn_danmu:    //弹幕
                readyGo(DanMuActivity.class);
                break;
        }
    }
}
