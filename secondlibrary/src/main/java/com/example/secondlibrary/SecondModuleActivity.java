package com.example.secondlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.loacarepo.utils.RouteConfigs;

import androidx.appcompat.app.AppCompatActivity;

@Route(path = RouteConfigs.SECOND_ACTIVITY)
public class SecondModuleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_module);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.e("wood121", url + "...second");

        findViewById(R.id.btn_skip).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_skip) {
            ARouter.getInstance().build(RouteConfigs.WOOD_ACTIVITY).navigation();
        }
    }
}
