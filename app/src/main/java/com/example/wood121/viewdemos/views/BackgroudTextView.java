package com.example.wood121.viewdemos.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by wood121 on 2017/8/31.
 * 丰富背景、多绘制几层背景
 */

@SuppressLint("AppCompatCustomView")
public class BackgroudTextView extends TextView {

    private Paint innerPaint;
    private Paint outerPaint;

    public BackgroudTextView(Context context) {
        super(context);

    }

    public BackgroudTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BackgroudTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        innerPaint = new Paint();
        innerPaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        innerPaint.setStyle(Paint.Style.FILL);

        outerPaint = new Paint();
        outerPaint.setColor(Color.YELLOW);
        outerPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("wood","onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //之前操作，先绘制我们的需求，然后绘制原本该要的需求
        //外层的圈圈
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), outerPaint);
        //内层的圈圈
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, innerPaint);

        canvas.save();
        canvas.translate(100, 0);

        super.onDraw(canvas);
        canvas.restore();
        Log.d("wood","onDraw");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("wood","onSizeChanged");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("wood","onFinishInflate");
    }
}
