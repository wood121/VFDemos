package com.example.wood121.viewdemos.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.example.wood121.viewdemos.util.DisplayUtils;

/**
 * Created by wood121 on 2017/10/16.
 * 圆弧比例图：内部圆、园内的文字，外部接的圆
 * <p>
 * q1：确保文字的位置居中？    paint.setTextAlign(Align.CENTER);
 * q2: 外部环形的绘制? 空心画笔带宽度、
 * q3: 获取屏幕宽度？  wm.getDeultDisplay().getMetrics(...DisplayMetrics)
 * q4: 起始位置固定设置，扫过的角度外部输入
 * q5: 设置动画
 * q6: 文字大小的设置 textView.setTextView(..sp单位的),paint.setTextSize(..px单位的)
 */

public class ArcPercentView extends View {

    private int width;
    private int widthPixels;
    private int heightPixels;
    private int length;
    private int circleMid;
    private Paint innerPaint;
    private Paint textPaint;
    private RectF mRectF;
    private Paint outerPaint;

    public ArcPercentView(Context context) {
        super(context);
        init(context);
    }

    public ArcPercentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ArcPercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //圆形图需要的参数
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
//        width = defaultDisplay.getWidth();  //已经过时的方法
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        widthPixels = outMetrics.widthPixels;
        heightPixels = outMetrics.heightPixels;

        circleMid = widthPixels / 2;
        length = widthPixels / 8;

        innerPaint = new Paint();
        innerPaint.setAntiAlias(true);
        innerPaint.setStyle(Paint.Style.FILL);
        innerPaint.setColor(Color.RED);

        //文字需要的参数:
        textPaint = new Paint();
        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(DisplayUtils.sp2px(context, 18));
        textPaint.setTextAlign(Paint.Align.CENTER); //起始点的位置
        /*
         外部环形需要的参数
         方法一：外接圆的位置、空心画笔带宽度、
         方法二：外接圆的位置、实心画笔
         */
        mRectF = new RectF(
                (float) (widthPixels * 0.2),
                (float) (widthPixels * 0.2),
                (float) (widthPixels * 0.8),
                (float) (widthPixels * 0.8)
        );
        outerPaint = new Paint();
        outerPaint.setColor(Color.GREEN);
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth((float) (widthPixels / 12));
//        outerPaint.setStrokeJoin(Paint.Join.ROUND);
        outerPaint.setStrokeCap(Paint.Cap.ROUND);   // 画笔的圆、还是方块边角

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
         外部的占用比例: x位置是0度的位置、mRectF外接矩形、true/false是否过圆心,
         */
        canvas.drawArc(mRectF, 135, 270, false, outerPaint);

        /*
         外部的实际位置设置
         */

        /*
         画中间的圆、文字
         */
        canvas.drawCircle(circleMid, circleMid, length, innerPaint);
        String drawText = "99";
        canvas.drawText(drawText, circleMid, circleMid, textPaint);
    }
}

