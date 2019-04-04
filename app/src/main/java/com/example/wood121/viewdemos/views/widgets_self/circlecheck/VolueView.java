package com.example.wood121.viewdemos.views.widgets_self.circlecheck;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.wood121.viewdemos.util.DisplayUtils;

import androidx.annotation.Nullable;

import static android.R.attr.offset;

/**
 * Created by wood121 on 2017/10/16.
 * 音频播放条纹：坐标计算、动画效果
 * 花生理财中：HorizontalScrollView + view(LinearGradient条纹状)
 */

public class VolueView extends View {
    private int mRectCount = 20;
    private int mWidth;
    private float mRectWidth = 5f;
    private Paint mPaint;
    private float currentHeight;
    private float mRectHeight;

    public VolueView(Context context) {
        super(context);
        init(context);
    }

    public VolueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VolueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();

        mWidth = DisplayUtils.getWindowWidth(context);
        currentHeight = 80f;
        mRectHeight = 90f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mRectCount; i++) {
            canvas.drawRect(
                    (float) (mWidth * 0.4 / 2 + mRectWidth * i + offset),
                    currentHeight,
                    (float) (mWidth * 0.4 / 2 + mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint
            );
        }
    }
}
