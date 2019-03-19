package com.example.wood121.viewdemos.views.charts;

import android.os.Bundle;
import android.util.Log;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.views.charts.widget.FanShapedView;
import com.example.wood121.viewdemos.views.charts.widget.SimpleLineChart;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 饼状图、折线图
 */
public class BingActivity extends AppCompatActivity {
    @BindView(R.id.shapeview)
    FanShapedView shapeview;
    @BindView(R.id.linechart)
    SimpleLineChart linechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing);
        ButterKnife.bind(this);


        shapeview.setStartAngle(-180);
        updateView(shapeview);

        String[] xItem = {"8/27", "8/28", "8/27", "8/27", "8/27", "8/27", "8/27"};
        String[] yItem = {"50", "40", "30", "20", "10"};
        if (linechart == null)
            Log.e("wing", "null!!!!");
        linechart.setXItem(xItem);
        linechart.setYItem(yItem);
        HashMap<Integer, Integer> pointMap = new HashMap();
//        pointMap.put(0,6);
//        pointMap.put(1,33);
//        pointMap.put(2,34);
//        pointMap.put(3,45);
//        pointMap.put(4,23);
//        pointMap.put(5,32);
//        pointMap.put(6,34);

        for (int i = 0; i < xItem.length; i++) {
            pointMap.put(i, (int) (Math.random() * 5));
        }
        linechart.setData(pointMap);
    }

    private void updateView(FanShapedView shapeview) {
        //颜色，权重
        int[][] array = {{0Xffffb760, 1}, {0Xff717acb, 0}, {0Xfffb694c, 1}, {0Xff50ceff, 1}};
        shapeview.setData(array);
    }

}
