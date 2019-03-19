package com.example.loacarepo;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.loacarepo.utils.RouteConfigs;

import androidx.appcompat.app.AppCompatActivity;

@Route(path = RouteConfigs.WOOD_ACTIVITY)
public class WoodActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wood);


        findViewById(R.id.btn_skip).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_skip) {
            ARouter.getInstance().build(RouteConfigs.SECOND_ACTIVITY).navigation();
        }
    }
}
