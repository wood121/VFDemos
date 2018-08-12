package com.example.wood121.viewdemos;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.example.wood121.viewdemos.util.MyCrashHandler;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wood121 on 2018/3/16.
 */

public class MyApplication extends Application {

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        MyCrashHandler.getInstance().init(getApplicationContext());
        MultiDex.install(this);
        Log.e("url", "MyApplication");

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        refWatcher = LeakCanary.install(this);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }
}
