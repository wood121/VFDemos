package com.example.wood121.viewdemos.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.views.TopBar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TopbarActivity extends AppCompatActivity {

    @InjectView(R.id.tb_view1)
    TopBar tbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbar);
        ButterKnife.inject(this);

        tbView.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TopbarActivity.this, "左侧按钮哈哈哈", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(TopbarActivity.this, "右侧按钮呵呵呵", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
