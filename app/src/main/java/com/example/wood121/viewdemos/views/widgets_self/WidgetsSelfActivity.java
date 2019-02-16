package com.example.wood121.viewdemos.views.widgets_self;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

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

    /**
     * mData.add(new ModelRecyclerBean("进度条-音视频", R.mipmap.circle_zip, ArcViewActivity.class));
     * mData.add(new ModelRecyclerBean("物流进度", R.mipmap.circle_zip, CheckProcessActivity.class));
     * mData.add(new ModelRecyclerBean("标题栏", R.mipmap.circle_zip, TopbarActivity.class));
     * mData.add(new ModelRecyclerBean("ViewActions", R.mipmap.circle_zip, ViewActionsActivity.class));
     */
    @Override
    protected void initPageViewListener() {
        findViewById(R.id.btn_arcview).setOnClickListener(this);
        findViewById(R.id.btn_checkprocess).setOnClickListener(this);
        findViewById(R.id.btn_topbar).setOnClickListener(this);
        findViewById(R.id.btn_viewaction).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_arcview:
                readyGo(ArcViewActivity.class);
                break;
            case R.id.btn_checkprocess:
                readyGo(CheckProcessActivity.class);
                break;
            case R.id.btn_topbar:
                readyGo(TopbarActivity.class);
                break;
            case R.id.btn_viewaction:
                readyGo(ViewActionsActivity.class);
                break;
        }
    }
}
