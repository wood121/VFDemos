package com.example.wood121.viewdemos.view_part;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;

/**
 * *****View的事件分发：https://blog.csdn.net/guolin_blog/article/details/9097463/
 * 1.OnTouchListener与OnClickListener的区别：
 * 现象：前者先执行，有3类动作监听；return true的情况下onClick不再执行。
 * 源码：在dispatchTouchEvent中最先执行的就是onTouch方法，onClick的调用是在onTouchEvent(event)方法中的。
 * 另注意：Button,ImageView的setOnTouchListener走的Action方法不一致，button是可以走三个的，但是imageview只走了一个ACTION_DOWN
 * 2.onTouch和onTouchEvent都是在dispatchTouchEvent中执行，前者优先，如果onTouch中返回true则后者不再执行。
 * onTouch执行有两个前提，必须设置了接口，必须是enable的控件。
 * <p>
 * *****ViewGroup的事件分发：https://blog.csdn.net/guolin_blog/article/details/9153747
 * 1.事件分发是先传到ViewGroup，再有VG传递到View的
 * 2.在ViewGroup中可以通过onInterceptTouchEvent方法对事件传递进行拦截，返回true则不允许事件继续向子view传递，返回false则代表不拦截（默认的）
 * 3.子view中如果将传递的事件消费掉，VG中将无法收到任何事件
 */
public class ViewActionsActivity extends AppCompatActivity {

    private Context mContext;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_actions);

        //View的事件分发
        viewActions();
        //ViewGroup的事件分发
        viewGroupActions();

        findViewById(R.id.btn_rec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActionsActivity.this, ViewActionRecActivity.class));
            }
        });
    }

    private void viewGroupActions() {
        findViewById(R.id.btn_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("wood", "btn_one--onclick");
            }
        });

        findViewById(R.id.btn_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("wood", "btn_two--onclick");
            }
        });

        findViewById(R.id.mylayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("wood", "mylayout--onTouch");
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void viewActions() {
        mContext = getApplicationContext();
        Button btnOo = findViewById(R.id.btn_oo);

        btnOo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("wood", "onClick");
                ToastUtil.showToast(mContext, "onClick");
            }
        });

        btnOo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("wood", "onTouch,action:" + event.getAction());
                return true;
            }
        });
    }


}
