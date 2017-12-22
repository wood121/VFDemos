package com.example.wood121.viewdemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.widget.CheckProcessView;
import com.example.wood121.viewdemos.widget.DrawHookView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 审核进度条
 */

public class CheckProcessActivity extends AppCompatActivity {


    @BindView(R.id.step1)
    DrawHookView step1;
    @BindView(R.id.step2)
    CheckProcessView step2;
    @BindView(R.id.step3)
    CheckProcessView step3;
    @BindView(R.id.step4)
    CheckProcessView step4;
    @BindView(R.id.step5)
    CheckProcessView step5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_process);
        ButterKnife.bind(this);

        initEvents();
    }

    private void initEvents() {
        Random random = new Random();
        int i = random.nextInt(2);
        if (i % 2 == 0) {
//            step1.setIsPass(true);
        } else {
            step2.setIsPass(false);
        }

        int test = 1;
        step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
