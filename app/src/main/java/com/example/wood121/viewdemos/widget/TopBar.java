package com.example.wood121.viewdemos.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;

/**
 * Created by wood121 on 2017/9/18.
 * 标题栏的封装
 */

public class TopBar extends RelativeLayout {

    private topbarClickListener mClickListener;
    private TextView tv_title;
    private Button btn_right;
    private Button btn_left;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取view：xml方式，new TextView(context)代码添加控件
        View topBar = LayoutInflater.from(context).inflate(R.layout.topbar, this, true);
        btn_left = (Button) topBar.findViewById(R.id.btn_left);
        btn_right = (Button) topBar.findViewById(R.id.btn_right);
        tv_title = (TextView) topBar.findViewById(R.id.tv_title);

        btn_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.leftClick();
            }
        });
        btn_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.rightClick();
            }
        });

        //获取属性，与view进行绑定操作
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        //整个图的设置    这个属性取值_下划线带出来的
        int titleBackgroudColor = ta.getResourceId(R.styleable.TopBar_title_backgroud_color, Color.WHITE);
        topBar.setBackgroundColor(titleBackgroudColor);

        boolean leftVisible = ta.getBoolean(R.styleable.TopBar_leftBtnVisible, true);
        btn_left.setVisibility(leftVisible ? View.VISIBLE : View.INVISIBLE);
        boolean rightVisible = ta.getBoolean(R.styleable.TopBar_rightBtnVisible, true);
        btn_right.setVisibility(rightVisible ? View.VISIBLE : View.INVISIBLE);

        //leftBtn
        String leftText = ta.getString(R.styleable.TopBar_leftText);
        if (!TextUtils.isEmpty(leftText)) {//文字内容,字体颜色
            btn_left.setText(leftText);
            int leftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, Color.BLACK);
            btn_left.setTextColor(leftTextColor);
        } else {    //设置背景图片？，如果有就设置，没有就默认返回箭头
            int leftRid = ta.getResourceId(R.styleable.TopBar_leftTextDrawable, R.mipmap.ic_launcher_round);
            if (leftRid != -1) {
                btn_left.setBackgroundResource(leftRid);
            }
        }

        //midText
        int titleTextDrawable = ta.getResourceId(R.styleable.TopBar_midTextDrawable, -1);
        if (titleTextDrawable != -1) {
            tv_title.setBackgroundResource(titleTextDrawable);
        } else {
            //如果不是图片标题 则获取文字标题
            String titleText = ta.getString(R.styleable.TopBar_midText);
            if (!TextUtils.isEmpty(titleText)) {
                tv_title.setText(titleText);
            }
            //获取标题显示颜色
            int titleTextColor = ta.getColor(R.styleable.TopBar_midTextColor, Color.WHITE);
            tv_title.setTextColor(titleTextColor);
        }

        //RightBtn
        String rightText = ta.getString(R.styleable.TopBar_rightText);
        if (!TextUtils.isEmpty(rightText)) {//文字内容,字体颜色
            btn_right.setText(rightText);
            int rightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, Color.BLACK);
            btn_right.setTextColor(rightTextColor);
        } else {    //设置背景图片？
            int rightRid = ta.getResourceId(R.styleable.TopBar_rightTextDrawable, -1);
            if (rightRid != -1) {
                btn_right.setBackgroundResource(rightRid);
            }
        }

        ta.recycle();
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface topbarClickListener {
        void leftClick();

        void rightClick();
    }

    public void setOnTopbarClickListener(topbarClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }


}
