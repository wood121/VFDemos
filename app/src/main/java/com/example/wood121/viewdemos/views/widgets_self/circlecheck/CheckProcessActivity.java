package com.example.wood121.viewdemos.views.widgets_self.circlecheck;

import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 圈圈
 */

public class CheckProcessActivity extends AppCompatActivity {
    @BindView(R.id.step1)
    DrawHookView step1;
    @BindView(R.id.step2)
    CheckProcessView step2;

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
