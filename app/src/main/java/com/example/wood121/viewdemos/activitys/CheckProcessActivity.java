package com.example.wood121.viewdemos.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.views.CheckProcessView;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *  审核进度条
 */

public class CheckProcessActivity extends AppCompatActivity {


    @InjectView(R.id.step1)
    CheckProcessView step1;
    @InjectView(R.id.step2)
    CheckProcessView step2;
    @InjectView(R.id.step3)
    CheckProcessView step3;
    @InjectView(R.id.step4)
    CheckProcessView step4;
    @InjectView(R.id.step5)
    CheckProcessView step5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_process);
        ButterKnife.inject(this);

        initEvents();
    }

    private void initEvents() {
        Random random = new Random();
        int i = random.nextInt(2);
        if (i % 2 == 0) {
            step1.setIsPass(true);
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
