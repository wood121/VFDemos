package com.example.wood121.viewdemos;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.example.wood121.viewdemos.util.MyCrashHandler;
import com.meituan.android.walle.WalleChannelReader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.commonsdk.UMConfigure;

import java.util.LinkedList;
import java.util.Queue;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wood121 on 2018/3/16.
 */

public class UserApp extends Application {

    private RefWatcher refWatcher;
    private static UserApp myApplication;

    public static UserApp getAppContext() {
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
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);

        //友盟初始化
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        Log.i("yicooll", "" + "*****************************" + channel);
        UMConfigure.init(this, "5bee8501f1f5567da000020c", channel, UMConfigure.DEVICE_TYPE_PHONE, "");
    }
}
