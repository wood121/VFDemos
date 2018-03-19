package com.example.wood121.viewdemos;

import android.app.Application;
import android.util.Log;

import com.example.wood121.viewdemos.util.MyCrashHandler;

/**
 * Created by wood121 on 2018/3/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyCrashHandler.getInstance().init(getApplicationContext());
        Log.e("url", "MyApplication");
    }
}
