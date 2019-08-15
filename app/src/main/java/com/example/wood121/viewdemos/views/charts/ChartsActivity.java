package com.example.wood121.viewdemos.views.charts;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.views.charts.linechart.LineChartActivity;
import com.example.wood121.viewdemos.views.charts.piechart.PieChartActivity;
import com.example.wood121.viewdemos.views.charts.proportion.ProportionActivity;

public class ChartsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_charts;
    }

    /**
     * mData.add(new ModelRecyclerBean("折线图1", R.mipmap.circle_zip, PieChartActivity.class));
     * mData.add(new ModelRecyclerBean("折线图2", R.mipmap.circle_zip, LineChartActivity.class));
     */
    @Override
    protected void initEvent() {
        findViewById(R.id.btn_bing).setOnClickListener(this);
        findViewById(R.id.btn_zhe).setOnClickListener(this);
        findViewById(R.id.btn_proportion).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bing:
                readyGo(PieChartActivity.class);
                break;
            case R.id.btn_zhe:
                readyGo(LineChartActivity.class);
                break;
            case R.id.btn_proportion:    //弹幕
                readyGo(ProportionActivity.class);
                break;
        }
    }
}
