package com.example.wood121.viewdemos.views.charts.linechart;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * 折线图
 */
public class LineChartActivity extends BaseActivity implements View.OnClickListener {

    private LineChart lineChart;
    private ArrayList<Model> list;
    private Random random;

    @Override
    protected void initData(Bundle savedInstanceState) {
        list = new ArrayList<>();
        random = new Random();
        initChartDatas(20);
    }

    private void initChartDatas(int numbers) {
        list.clear();
        for (int i = 0; i < numbers; i++) {
            Model model = new Model();
            model.percent = -10 + random.nextFloat() * 21;//(-10,10)
            model.date = (int) (1 + Math.random() * 13) + "." + (int) (1 + Math.random() * 31);
            list.add(model);
        }
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_zhe;
    }

    @Override
    protected void initEvent() {
        lineChart = findViewById(R.id.line_chart);
        lineChart.setDrawPoints(false).setFillArea(true).setPlayAnim(true);
        startLine();

        findViewById(R.id.btn_10).setOnClickListener(this);
        findViewById(R.id.btn_20).setOnClickListener(this);
        findViewById(R.id.btn_30).setOnClickListener(this);
        findViewById(R.id.btn_50).setOnClickListener(this);
        findViewById(R.id.btn_100).setOnClickListener(this);
    }

    private void startLine() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lineChart.setDatas(list);
            }
        }, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_10:
                initChartDatas(10);
                break;
            case R.id.btn_20:
                initChartDatas(20);
                break;
            case R.id.btn_30:
                initChartDatas(30);
                break;
            case R.id.btn_50:
                initChartDatas(50);
                break;
            case R.id.btn_100:
                initChartDatas(100);
                break;
        }
        startLine();
    }
}
