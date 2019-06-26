package com.example.wood121.viewdemos.apis.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wood121.viewdemos.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 每次启动一个activity都创建一个新的实例，
 * 谁启动了它，它就是属于谁的任务栈taskId
 */
public class ModeStandardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_standard);

        findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModeStandardActivity.this, ModeStandardActivity.class));
            }
        });
    }


}
