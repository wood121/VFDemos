package com.example.wood121.viewdemos.views.widgets;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.views.widgets.RecyclerView.RecActivity;

public class WidgetsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_components;
    }

    /**
     * mData.add(new ModelRecyclerBean("dialog_context", R.mipmap.circle_captcha, ContextActivity.class));
     * mData.add(new ModelRecyclerBean("RecView", R.mipmap.circle_zip, RecActivity.class));
     */
    @Override
    protected void initPageViewListener() {
        findViewById(R.id.btn_rec).setOnClickListener(this);
        findViewById(R.id.btn_dialog_context).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rec:
                readyGo(RecActivity.class);
                break;
            case R.id.btn_dialog_context:
                readyGo(ContextActivity.class);
                break;
        }
    }
}