package com.example.wood121.viewdemos.views.widgets_self.circlecheck;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by wood121 on 2017/8/31.
 */

@SuppressLint("AppCompatCustomView")
public class ShiningTextView extends TextView {

    private int measuredWidth;
    private TextPaint textPaint;
    private LinearGradient linearGradient;
    private Matrix matrix;
    private int mTranslate;

    public ShiningTextView(Context context) {
        super(context);
    }

    public ShiningTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShiningTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (measuredWidth == 0) {
            measuredWidth = getMeasuredWidth();
        }
        if (measuredWidth > 0) {
            textPaint = getPaint();
            linearGradient = new LinearGradient(0, 0, measuredWidth, 0, new int[]{Color.BLUE, Color.RED, Color.YELLOW},
                    null, Shader.TileMode.CLAMP);
            textPaint.setShader(linearGradient);
            matrix = new Matrix();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (matrix != null) {
            mTranslate += measuredWidth / 5;
            if (mTranslate > 2 * measuredWidth) {
                mTranslate = -measuredWidth;
            }
            matrix.setTranslate(mTranslate, 0);
            linearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(100);
        }
    }
}
