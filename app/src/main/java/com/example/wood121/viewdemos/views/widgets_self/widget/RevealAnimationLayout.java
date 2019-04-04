package com.example.wood121.viewdemos.views.widgets_self.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RevealAnimationLayout extends FrameLayout {
    public Path mClipPath;
    public Path mOpClipPath;
    public Paint mPaint;
    public RectF mLayer;

    public ValueAnimator mStartingAnimator;
    public float mAnimatorValue;
    private static final int DEFAULT_DURATION = 400;
    /**
     * 缩放中心
     */
    private int mCenterX;
    private int mCenterY;
    /**
     * 扩散边点
     */
    private int mExpandX;
    private int mExpandY;

    public void setCenter(int cX, int cY) {
        this.mCenterX = cX;
        this.mCenterY = cY;
    }

    public void setExpandPoint(int expandX, int expandY) {
        this.mExpandX = expandX;
        this.mExpandY = expandY;
    }

    public enum AnimaType {
        Circle, BackCircle
    }

    public AnimaType mAnimaType = AnimaType.Circle;


    public RevealAnimationLayout(@NonNull Context context) {
        this(context, null);
    }

    public RevealAnimationLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RevealAnimationLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr();
    }

    private void initAttr() {
        mClipPath = new Path();
        mOpClipPath = new Path();
        mLayer = new RectF();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);

        setWillNotDraw(false);

        initAnimator();
    }

    /**
     * 初始化动画类
     */
    private void initAnimator() {
        mStartingAnimator = new ValueAnimator().setDuration(DEFAULT_DURATION);
        mStartingAnimator.setInterpolator(new AccelerateInterpolator());
        mStartingAnimator.addUpdateListener(animation -> {

            //拿到动画的执行的百分比mAnimatorValue
            mAnimatorValue = (float) animation.getAnimatedValue();
            //开启动画后 刷新页面
            invalidate();
        });
        mStartingAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (mOnLayoutAnimaotrEndListener != null) {
                    mOnLayoutAnimaotrEndListener.animatorEnd();
                }
            }
        });
    }

    private onLayoutAnimaotrEndListener mOnLayoutAnimaotrEndListener;

    public interface onLayoutAnimaotrEndListener {

        void animatorEnd();
    }

    public void setOnLayoutAnimaotrEndListener(onLayoutAnimaotrEndListener listener) {
        this.mOnLayoutAnimaotrEndListener = listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        mRCHelper.onSizeChanged(this, w, h);
        mLayer.set(0, 0, w, h);
        refreshRegion(this);
    }

    public void refreshRegion(View view) {
        int w = (int) mLayer.width();
        int h = (int) mLayer.height();
        RectF areas = new RectF();
        areas.left = view.getPaddingLeft();
        areas.top = view.getPaddingTop();
        areas.right = w - view.getPaddingRight();
        areas.bottom = h - view.getPaddingBottom();
        mClipPath.reset();

        //缩放中心
        PointF center = new PointF(mCenterX, mCenterY);
        //缩放半径
        float rX = Math.abs(center.x - mExpandX);
        float rY = Math.abs(center.y - mExpandY);
        float rMax = (float) Math.hypot(rX, rY);
        float r = rMax * mAnimatorValue;
        //clip canvas
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            mClipPath.addCircle(center.x, center.y, r, Path.Direction.CW);
            mClipPath.moveTo(0, 0);  // 通过空操作让Path区域占满画布
            mClipPath.moveTo(w, h);
        } else {
            float y = h / 2 - r;
            mClipPath.moveTo(areas.left, y);
            mClipPath.addCircle(center.x, center.y, r, Path.Direction.CW);
        }
    }

    /**
     * 如果没有设置背景是不会调用这个的
     *
     * @param canvas
     */

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(mLayer, null, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        onClipDraw(canvas);
        canvas.restore();
    }

    public void onClipDraw(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        mOpClipPath.reset();
        mOpClipPath.addRect(0, 0, mLayer.width(), mLayer.height(), Path.Direction.CW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mOpClipPath.op(mClipPath, Path.Op.DIFFERENCE);
        }
        canvas.drawPath(mOpClipPath, mPaint);
    }

    @Override
    public void invalidate() {
        refreshRegion(this);
        super.invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mStartingAnimator != null) {
            mStartingAnimator.cancel();
            mStartingAnimator.removeAllUpdateListeners();
            mStartingAnimator.removeAllListeners();
        }
    }

    /**
     * 开启动画
     *
     * @param animaType 动画类型
     */
    public void startAnimal(AnimaType animaType) {
        this.mAnimaType = animaType;
//        setVisibility(View.VISIBLE);
        mStartingAnimator.cancel();
        if (mAnimaType == AnimaType.BackCircle) {
            mStartingAnimator.setFloatValues(1, 0);
        } else {
            mStartingAnimator.setFloatValues(0, 1);
        }
        mStartingAnimator.start();
    }

    public void setLayoutVisible() {
        mStartingAnimator.cancel();
        mStartingAnimator.setFloatValues(1, 1);
        mStartingAnimator.start();
    }
}
