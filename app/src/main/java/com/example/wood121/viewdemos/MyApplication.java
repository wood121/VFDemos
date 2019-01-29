package com.example.wood121.viewdemos;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.example.wood121.viewdemos.util.MyCrashHandler;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.commonsdk.UMConfigure;

import java.util.LinkedList;
import java.util.Queue;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wood121 on 2018/3/16.
 */

public class MyApplication extends Application {

    private RefWatcher refWatcher;
    private static MyApplication myApplication;

    public static MyApplication getAppContext() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        myApplication = this;
        Queue<Object> objects = new LinkedList<>();
        super.onCreate();
        Log.e("url", "MyApplication");
        //crash信息收集
        MyCrashHandler.getInstance().init(getApplicationContext());
        //65534个方法类的
        MultiDex.install(this);
        //内存泄漏分析
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);

        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        //友盟统计
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);

    }
}
