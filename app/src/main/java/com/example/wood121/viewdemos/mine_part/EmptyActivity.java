package com.example.wood121.viewdemos.mine_part;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wood121.viewdemos.R;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        finish();
    }
}
