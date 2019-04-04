package com.example.wood121.viewdemos.views.widgets_self.topbar;

import android.os.Bundle;

import com.example.wood121.viewdemos.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public class TopbarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbar);
        ButterKnife.bind(this);

//        tbView.setOnTopbarClickListener(new TopBar.topbarClickListener() {
//            @Override
//            public void leftClick() {
//                Toast.makeText(TopbarActivity.this, "左侧按钮哈哈哈", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void rightClick() {
//                Toast.makeText(TopbarActivity.this, "右侧按钮呵呵呵", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

}
