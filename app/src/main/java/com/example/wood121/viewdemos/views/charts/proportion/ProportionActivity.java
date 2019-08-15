package com.example.wood121.viewdemos.views.charts.proportion;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.zhouyou.view.segmentedbar.Segment;
import com.zhouyou.view.segmentedbar.SegmentedBarView;

import java.util.ArrayList;

public class ProportionActivity extends BaseActivity {

    private int hour = 15;

    @Override
    protected void initData(Bundle savedInstanceState) {
        
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_proportion;
    }

    @Override
    protected void initEvent() {
        SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.barView);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "Low", Color.parseColor("#EF3D2F"));
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 9.5f, "Optimal", Color.parseColor("#8CC63E"));
        segments.add(segment2);
        Segment segment3 = new Segment(9.5f, 20f, "High", Color.parseColor("#EF3D2F"));
        segments.add(segment3);
        barView.setValueWithUnit(13.96f, "10<sup>12</sup>/l");
        //barView.setSegmentSideRule(SegmentedBarViewSideRule.average);//通过代码设置规则
        //barView.setValue(13.96f);
        //barView.setValue(13.96f,"Optimal");
        barView.setSegments(segments);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.shape_radius_back));
        progressBar.setProgress(50);
    }
}
