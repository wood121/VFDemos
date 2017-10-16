package com.example.wood121.viewdemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wood121.viewdemos.activitys.ArcViewActivity;
import com.example.wood121.viewdemos.activitys.BingActivity;
import com.example.wood121.viewdemos.activitys.CheckProcessActivity;
import com.example.wood121.viewdemos.activitys.GoodsTransformActivity;
import com.example.wood121.viewdemos.activitys.RecActivity;
import com.example.wood121.viewdemos.activitys.TopbarActivity;
import com.example.wood121.viewdemos.activitys.ZheActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.bt_goodsTransformProcess)
    Button btGoodsTransformProcess;
    @InjectView(R.id.bt_checkProcesss)
    Button btCheckProcesss;
    @InjectView(R.id.bt_bing)
    Button btBing;
    @InjectView(R.id.bt_zhe)
    Button bt_zhe;
    @InjectView(R.id.bt_topbar)
    Button bt_topbar;
    @InjectView(R.id.bt_recycleView)
    Button bt_recycleView;
    @InjectView(R.id.bt_viewDemo3)
    Button bt_viewDemo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.bt_goodsTransformProcess, R.id.bt_viewDemo3,R.id.bt_recycleView, R.id.bt_topbar, R.id.bt_zhe, R.id.bt_bing, R.id.bt_checkProcesss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_goodsTransformProcess:
                startActivity(new Intent(this, GoodsTransformActivity.class));
                Toast.makeText(this, "hha", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_checkProcesss:
                startActivity(new Intent(this, CheckProcessActivity.class));
                break;
            case R.id.bt_bing:
                startActivity(new Intent(this, BingActivity.class));
                break;
            case R.id.bt_zhe:
                startActivity(new Intent(this, ZheActivity.class));
                break;
            case R.id.bt_topbar:
                startActivity(new Intent(this, TopbarActivity.class));
                break;
            case R.id.bt_recycleView:
                startActivity(new Intent(this, RecActivity.class));
                break;
            case R.id.bt_viewDemo3:
                startActivity(new Intent(this, ArcViewActivity.class));
                break;
        }
    }
}
