package com.example.wood121.viewdemos.views.charts.linechart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @date: 2019/7/18
 * @version:
 * @author: liuzhengling
 * @des:
 */
public class MyLineChart extends View {

    private Paint axisPaint;

    public MyLineChart(Context context) {
        super(context);
        init(context);
    }

    public MyLineChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyLineChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //坐标轴的笔
        axisPaint = new Paint();
        axisPaint.setAntiAlias(true);
        axisPaint.setColor(Color.BLACK);
        //TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, getResources().getDisplayMetrics())
        axisPaint.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, getResources().getDisplayMetrics()));

        //横向划分线的笔


        //坐标点的笔

        //连接线的笔

    }

    /**
     * 外界传入数据
     */
    public void setLineData(List<Model> list) {
        //

    }

    /**
     *
     */

}
