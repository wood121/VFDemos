package com.example.wood121.viewdemos.views.widgets_self.base;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.DisplayUtils;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * <p>描述：https://blog.csdn.net/harvic880925/article/details/50995268
 * 《自定义三部曲之 绘图篇》
 * 1.点线圆、RectF--矩形、圆角矩形、椭圆、弧形
 * 2.Path
 * 3.Region
 * 4.Canvas.translate/rotate/scale/skew, clip..., sava/restore
 * 5.
 * </p>
 * 作者： wood121<br>
 * 日期： 2019/4/8 11:02<br>
 * 版本： v2.0<br>
 */
public class GeometryView extends View {
    private Context mContext;
    private Paint mBasePaint;
    private Path mPath;
    private float mX;
    private float mY;
    private float mPreX;
    private float mPreY;
    private int mItemWaveLength = 400;
    private int dx;
    private int dy;
    private Bitmap mBitmap;

    public GeometryView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public GeometryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GeometryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPath = new Path();

        //paint：抗锯齿、颜色、样式、画笔宽度、阴影
        mBasePaint = new Paint();
        mBasePaint.setAntiAlias(true);
//        mBasePaint.setARGB(255, 200, 100, 100);
//        mBasePaint.setColor(Color.RED);
//        mBasePaint.setStrokeWidth(5);
//        mBasePaint.setStrokeWidth(80);
//        mBasePaint.setStyle(Paint.Style.FILL);
//        mBasePaint.setStyle(Paint.Style.STROKE);
//        mBasePaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mBasePaint.setShadowLayer(10, 15, 15, Color.GREEN); //阴影的倾斜度、水平位移、数值位移

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bitmapmatrix);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRGB(255, 255, 255);
//        drawBaseGeometry(canvas);
//        drawPath(canvas);
//        drawText(canvas);

//        drawRange(canvas);
//        operateCanvas(canvas);
//        drawBezier(canvas);

//        quadTo();
//        rQuadTo();
//        drawWave(canvas);

//        setStrokeCap(canvas);
//        setStrokeJoin(canvas);
//        setPathEffect(canvas);

//        colorMatrix(canvas);
//        colorMatrixBitmap(canvas);

//        canvasLayer(canvas);
    }

    /**
     * Canvas与图层
     *
     * @param canvas
     */
    private void canvasLayer(Canvas canvas) {

    }

    /**
     * 图片的蓝色通道
     * 色彩的平移运算：增加指定颜色饱和度、色彩反转/反相功能
     *
     * @param canvas
     */
    private void colorMatrixBitmap(Canvas canvas) {
        // 生成色彩矩阵
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 50,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });
        mBasePaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, null, new Rect(0, 0, 500,
                500 * mBitmap.getHeight() / mBitmap.getWidth()), mBasePaint);
    }

    /**
     * 一般色系蓝色通道
     *
     * @param canvas
     */
    private void colorMatrix(Canvas canvas) {
        canvas.drawRect(0, 0, 500, 600, mBasePaint);

        canvas.translate(550, 0);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });
        mBasePaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawRect(0, 0, 500, 600, mBasePaint);
    }

    /**
     * @param canvas
     */
    private void setPathEffect(Canvas canvas) {

    }

    /**
     * 线转角连接形状处理
     *
     * @param canvas
     */
    private void setStrokeJoin(Canvas canvas) {
        mPath.moveTo(100, 100);
        mPath.lineTo(450, 100);
        mPath.lineTo(100, 300);
        mBasePaint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(mPath, mBasePaint);

        mPath.moveTo(100, 400);
        mPath.lineTo(450, 400);
        mPath.lineTo(100, 600);
        mBasePaint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(mPath, mBasePaint);

        mPath.moveTo(100, 700);
        mPath.lineTo(450, 700);
        mPath.lineTo(100, 900);
        mBasePaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(mPath, mBasePaint);

    }

    /**
     * 设置线帽样式，取值有Cap.ROUND(圆形线帽)、Cap.SQUARE(方形线帽)、Paint.Cap.BUTT(无线帽)
     * 安卓中：圆形和方形线帽
     *
     * @param canvas
     */
    private void setStrokeCap(Canvas canvas) {
        mBasePaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100, 200, 400, 200, mBasePaint);

        mBasePaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100, 400, 400, 400, mBasePaint);

        mBasePaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100, 600, 400, 600, mBasePaint);
    }

    /**
     * 绘制流动波浪
     *
     * @param canvas
     */
    private void drawWave(Canvas canvas) {
        //绘制全面屏的波纹：波长、振幅
        int halfRed = mItemWaveLength / 2;
        int originY = 500;
        mPath.moveTo(-mItemWaveLength + dx, originY);
        //这个角度会从0到mItemWaveLength，左边开始会有个缺口
//        mPath.moveTo(dx, originY);
        for (int i = -mItemWaveLength; i < getWidth() + mItemWaveLength; i += mItemWaveLength) {
            mPath.rQuadTo(halfRed / 2, -200, halfRed, 0);
            mPath.rQuadTo(halfRed / 2, 200, halfRed, 0);
        }
        //将波纹闭合起来
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());
        mPath.close();

        canvas.drawPath(mPath, mBasePaint);
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            dx = (int) animation.getAnimatedValue();
//            dy = (int) animation.getAnimatedValue();
            postInvalidate();
        });
        animator.start();
    }

    /**
     * (100,300)(300,300)(500,300)，三点绘制二阶贝塞尔曲线--相对坐标方式
     */
    private void rQuadTo() {
        //        mPath.rQuadTo(50,-100,200,0);
//        mPath.rQuadTo(150,100,200,0);
//        canvas.drawPath(mPath, mBasePaint);
    }

    /**
     * (100,300)(300,300)(500,300)，三点绘制二阶贝塞尔曲线--绝对坐标方式
     */
    private void quadTo() {
//        mPath.moveTo(100, 300);
//        mPath.quadTo(150, 200, 300, 300);
//        mPath.quadTo(450, 400, 500, 300);
//        canvas.drawPath(mPath, mBasePaint);
    }

    public void reset() {
        mPath.reset();
        invalidate();
    }

    /**
     * 二阶贝尔赛：ABC三个点，B点作为控制点、BC之间终点位置作为终点。平滑手势轨迹。
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                Log.e("wood", "ACTION_DOWN");
//                mPreX = event.getX();
//                mPreY = event.getY();
//                mPath.moveTo(mPreX, mPreY);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.e("wood", "ACTION_MOVE");
//                //终点
//                float x = event.getX();
//                float y = event.getY();
//                float endX = (mPreX + x) / 2;
//                float endY = (mPreY + y) / 2;
//                mPath.quadTo(mPreX, mPreY, endX, endY);
//                //更新控制点
//                mPreX = x;
//                mPreY = y;
//                //刷新界面
//                invalidate();
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e("wood", "ACTION_UP");
//
//                break;
//        }

        return true;
    }

    /**
     * 贝塞尔曲线
     *
     * @param canvas
     */
    private void drawBezier(Canvas canvas) {

    }

    /**
     * Canvas的变换：translate,Rotate,
     *
     * @param canvas
     */
    private void operateCanvas(Canvas canvas) {
//        Paint paint_green = generatePaint(Color.GREEN, Paint.Style.FILL, 5);
//        Paint paint_red = generatePaint(Color.RED, Paint.Style.STROKE, 5);
//
//        Rect rect = new Rect(300, 10, 500, 100);
//        canvas.drawRect(rect, paint_red); //画出原轮廓
//
//        canvas.rotate(45);//顺时针旋转画布
//        canvas.drawRect(rect, paint_green);//画出旋转后的矩形

        canvas.drawColor(Color.RED);
        Path path = new Path();
        path.addCircle(500, 500, 350, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawColor(Color.GREEN);
        canvas.drawText("哈哈", 500, 500, mBasePaint);
    }

    private Paint generatePaint(int green, Paint.Style style, int i) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(green);
        paint.setStyle(style);
        paint.setStrokeWidth(i);
        return paint;
    }

    /**
     * Region区域的响应
     * Region region = new Region(rect);
     * setEmpty,set,
     * setPath
     *
     * @param canvas
     */
    private void drawRange(Canvas canvas) {
//        Rect rect = new Rect(10, 10, 300, 200);
//        Region region = new Region(rect);
//        if (region.contains(105, 105)) {
//            Log.e("wood", "哈哈，这里面有");
//        }

//         //无论调用Set系列函数的Region是不是有区域值，当调用Set系列函数后，原来的区域值就会被替换成Set函数里的区域。
//        region.set(400, 400, 600, 800);

//        Path path = new Path();
//        RectF rectF = new RectF(50, 50, 200, 500);
//        path.addOval(rectF, Path.Direction.CW);
////        canvas.drawPath(path, mBasePaint);
//
//        Region region = new Region();
//        region.setPath(path, new Region(50, 50, 200, 400));


        Rect rect1 = new Rect(100, 100, 400, 200);
        Rect rect2 = new Rect(200, 0, 300, 300);
        canvas.drawRect(rect1, mBasePaint);
        canvas.drawRect(rect2, mBasePaint);

        Region region = new Region(rect1);
        Region r2 = new Region(rect2);
        region.op(r2, Region.Op.INTERSECT); //取交集

        //画出合并的部分
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        RegionIterator iter = new RegionIterator(region);
        Rect rectNew = new Rect();
        while (iter.next(rectNew)) {
            canvas.drawRect(rectNew, paint);
        }
    }

    private void drawText(Canvas canvas) {
//        mBasePaint.setTextAlign(Paint.Align.CENTER); //文字对齐方式
        mBasePaint.setTextSize(DisplayUtils.dp2px(mContext, 36));

//        mBasePaint.setFakeBoldText(true);//设置是否为粗体文字
//        mBasePaint.setUnderlineText(true);//设置下划线
//        mBasePaint.setTextSkewX((float) -0.25);//设置字体水平倾斜度，普通斜体字是-0.25
//        mBasePaint.setStrikeThruText(true);//设置带有删除线效果
//        mBasePaint.setTextScaleX(2);//只会将水平方向拉伸，高度不会变

    }

    /**
     * drawPath：直线与圆；RectF--矩形、圆角矩形、椭圆；线段轨迹
     * drawText
     *
     * @param canvas
     */
    private void drawPath(Canvas canvas) {
        Path path = new Path();
//        path.moveTo(100, 100);
//        path.lineTo(100, 300);
//        path.lineTo(399, 300);
//        path.close();

//        path.addCircle(400, 400, 100, Path.Direction.CW);

        RectF rectF = new RectF(100, 100, 400, 300);
//        path.addRect(rectF, Path.Direction.CW);
//        path.addRoundRect(rectF, 10, 10, Path.Direction.CW);
//        path.addOval(rectF,Path.Direction.CW);
        path.addArc(rectF, 90, 45); //x,y坐标的原始起点在左上角

        canvas.drawPath(path, mBasePaint);

    }

    /**
     * 点线圆、RetF--矩形、圆角矩形、椭圆、弧
     */
    private void drawBaseGeometry(Canvas canvas) {
//        canvas.drawPoint(200, 200, mBasePaint);
//        canvas.drawLine(0, 0, 100, 100, mBasePaint);
//        canvas.drawCircle(200, 200, 100, mBasePaint);

        RectF rectF = new RectF(100, 100, 300, 200);
//        canvas.drawRect(rectF,mBasePaint);
//        canvas.drawRoundRect(rectF,5,5,mBasePaint);
//        canvas.drawOval(rectF,mBasePaint);
        canvas.drawArc(rectF, 0, 90, true, mBasePaint);
    }
}
