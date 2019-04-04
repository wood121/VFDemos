package com.example.wood121.viewdemos.views.widgets_self.circlecheck;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by wood121 on 2017/8/7.
 */

public class CheckProcessView extends View {
    private Paint passPaint;
    private boolean isPass;


    public CheckProcessView(Context context) {
        super(context);
        init();
    }

    public CheckProcessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CheckProcessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        passPaint = new Paint();
        passPaint.setStrokeWidth(5);
        passPaint.setStyle(Paint.Style.STROKE);
        passPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            //view两把的宽度，包裹的实际内容的宽度
            width = getPaddingLeft() + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = getPaddingTop() + getPaddingBottom();
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画外围的圆操作
         */
        int center = getWidth() / 2;
        int radius = getWidth() / 2 - 5;
        int left = center - radius - 1;
        int right = center + radius + 1;
        RectF rectF = new RectF(left, left, right, right);

        int line1_x = getWidth() / 4;
        if (isPass) {
            passPaint.setColor(Color.GREEN);
            canvas.drawArc(rectF, 0, 360, false, passPaint);
            //画第一根线
            canvas.drawLine(line1_x, center, center, center + line1_x, passPaint);
            //画第二根线
            canvas.drawLine(center, center + line1_x, center + line1_x, center - line1_x, passPaint);
        } else {
            passPaint.setColor(Color.RED);
            canvas.drawArc(rectF, 0, 360, false, passPaint);
            canvas.drawLine(center - line1_x, center - line1_x, center + line1_x, center + line1_x, passPaint);
            canvas.drawLine(center - line1_x, center + line1_x, center + line1_x, center - line1_x, passPaint);
        }

    }

    public void setIsPass(Boolean isPass) {
        this.isPass = isPass;
    }

}
