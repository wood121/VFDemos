package com.example.wood121.viewdemos.views.widgets_self.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;


/**
 * 《Android自定义控件三部曲文章索引》
 * https://blog.csdn.net/harvic880925/article/details/50995268
 * 动画篇
 * 绘图篇
 * 视图篇
 */
public class View3StepActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout mFlContainer;
    private GeometryView mGeometryView;

    private SeekBar mSeekBar;
    private ImageView mImageView;
    private Bitmap mOriginBmp, mTempBmp;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_arc_view;
    }

    @Override
    protected void initEvent() {
        mFlContainer = findViewById(R.id.fl_container);
//        mFlContainer.addView(new GeometryView(this));

        findViewById(R.id.btn_reset).setOnClickListener(this);
        mGeometryView = findViewById(R.id.geometryView);
        mGeometryView.startAnim();

        //ColorMatrix
        mImageView = findViewById(R.id.img);
        mSeekBar = findViewById(R.id.seekbar);
        mOriginBmp = BitmapFactory.decodeResource(getResources(), R.drawable.bitmapmatrix);
        mTempBmp = Bitmap.createBitmap(mOriginBmp.getWidth(), mOriginBmp.getHeight(),
                Bitmap.Config.ARGB_8888);

        mSeekBar.setMax(20);
        mSeekBar.setProgress(1);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Bitmap bitmap = handleColorMatrixBmp(progress);
                mImageView.setImageBitmap(bitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private Bitmap handleColorMatrixBmp(int progress) {
        // 创建一个相同尺寸的可变的位图区,用于绘制调色后的图片
        Canvas canvas = new Canvas(mTempBmp); // 得到画笔对象
        Paint paint = new Paint(); // 新建paint
        paint.setAntiAlias(true); // 设置抗锯齿,也即是边缘做平滑处理
        ColorMatrix mSaturationMatrix = new ColorMatrix();
        mSaturationMatrix.setSaturation(progress);

        paint.setColorFilter(new ColorMatrixColorFilter(mSaturationMatrix));// 设置颜色变换效果
        canvas.drawBitmap(mOriginBmp, 0, 0, paint); // 将颜色变化后的图片输出到新创建的位图区
        // 返回新的位图，也即调色处理后的图片
        return mTempBmp;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset:
                mGeometryView.reset();
                break;
        }
    }
}

