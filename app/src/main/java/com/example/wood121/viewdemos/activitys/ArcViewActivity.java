package com.example.wood121.viewdemos.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wood121.viewdemos.R;

import butterknife.ButterKnife;

public class ArcViewActivity extends AppCompatActivity {

//    @InjectView(R.id.arcPercentView)
//    ArcPercentView arcPercentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_view);
        ButterKnife.inject(this);


    }
}
