package com.example.wood121.viewdemos.views.charts.piechart;

import android.os.Bundle;

import com.example.wood121.viewdemos.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 饼状图
 */
public class PieChartActivity extends AppCompatActivity {
    @BindView(R.id.shapeview)
    FanShapedView shapeview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing);
        ButterKnife.bind(this);

        shapeview.setStartAngle(-180);
        updateView(shapeview);
    }

    private void updateView(FanShapedView shapeview) {
        //颜色，权重
        int[][] array = {{0Xffffb760, 1}, {0Xff717acb, 0}, {0Xfffb694c, 1}, {0Xff50ceff, 1}};
        shapeview.setData(array);
    }

}
