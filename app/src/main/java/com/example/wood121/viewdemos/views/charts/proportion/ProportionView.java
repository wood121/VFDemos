package com.example.wood121.viewdemos.views.charts.proportion;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.wood121.viewdemos.R;

import androidx.annotation.Nullable;

/**
 * @date: 2019/7/27
 * @version:
 * @author: liuzhengling
 * @des:
 */
public class ProportionView extends View {

    private static final int DP_10 = 10;
    private Paint bgPaint;
    private Paint numberPaint;

    public ProportionView(Context context) {
        super(context);
        init(context, null);
    }

    public ProportionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProportionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //自定义属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProportionView);
        float lineWidth = ta.getDimensionPixelSize(R.styleable.ProportionView_line_width, dp2px(DP_10));
        int bgColor = ta.getColor(R.styleable.ProportionView_bg_color, Color.GRAY);
        int numberColor = ta.getColor(R.styleable.ProportionView_number_color, Color.RED);
        ta.recycle();

        //创建画笔
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(bgColor);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setStrokeWidth(lineWidth);

        numberPaint = new Paint();
        numberPaint.setAntiAlias(true);
        numberPaint.setColor(numberColor);
        numberPaint.setStyle(Paint.Style.FILL);
        numberPaint.setStrokeWidth(lineWidth);
    }

    public int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());
    }

    public int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    public int px2sp(float pxValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public void setDatas(int number, int totalNumber) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int w4 = getWidth() / 4;
        int length = width - w4;

        canvas.drawLine(0, 0, width / 2, 0, bgPaint);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(width / 2 - w4, 0, width, 0, bgPaint);

        canvas.drawLine(0, 0, length / 2, 0, numberPaint);
        numberPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(length / 2 - w4, 0, length, 0, numberPaint);
    }
}
