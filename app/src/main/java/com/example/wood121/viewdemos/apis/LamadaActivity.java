package com.example.wood121.viewdemos.apis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.wood121.viewdemos.R;

public class LamadaActivity extends AppCompatActivity {

    private Button mBtnLamada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamada);
        mBtnLamada = (Button) findViewById(R.id.btn_lamada);

        demo1();
        demo2();
        demo3();
    }

    /**
     * 有参数+代码块：适用于匿名内部类中方法不止一个参数的情况
     */
    private void demo3() {

    }

    /**
     * 有参数+语句：适用于匿名内部类中方法只有一个参数的情况
     */
    private void demo2() {
//        mBtnLamada.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        mBtnLamada.setOnClickListener((view) -> {
//            mBtnLamada.setText("我自己设置的lamada表达式");
//        });
//
//        //有参数+语句
//        mBtnLamada.setOnClickListener(v-> Log.e("wood121","hao"));

    }

    /**
     * 无参数+语句(代码块)：适用于匿名内部类中方法无参数的情况
     */
    private void demo1() {
//        //正常的写法
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //需要实现的逻辑代码
//            }
//        }).start();
//
//        //代码块
//        new Thread(() -> {
//
//        }).start();
//
//        //语句
//        new Thread(()-> Log.e("wood121","hao")).start();

    }
}
