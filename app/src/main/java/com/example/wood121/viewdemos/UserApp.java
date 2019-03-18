package com.example.wood121.viewdemos;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wood121.viewdemos.util.MyCrashHandler;
import com.meituan.android.walle.WalleChannelReader;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.util.LinkedList;
import java.util.Queue;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wood121 on 2018/3/16.
 */

public class UserApp extends MultiDexApplication {

    private static UserApp myApplication;
    //201902281515--微信的还需要审核
    private static final String mWXAppID = "wxd9ce22c68070fc42";
    private static final String mWXAppSecret = "f03d3d7d1374cb7eac942537922a98cc";
    //qq的还未通过开发者认证
    private static final String mQQAppID = "1105974383";
    private static final String mQQAppSecret = "Ck6IhpKVd2zUU8NC";
    //微博的还未通过开发者认证
    private static final String mWBAppID = "2753635618";
    private static final String mWBAppSecret = "bf021ea3a1688db5cd18c0eb47e93801";


    public static UserApp getAppContext() {
        return myApplication;
    }

    private static final boolean isDebug = true;

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
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        RefWatcher refWatcher = LeakCanary.install(this);

        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //友盟统计
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);

        //友盟初始化
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        Log.i("yicooll", "" + "*****************************" + channel);
        UMConfigure.init(this, "5bee8501f1f5567da000020c", channel, UMConfigure.DEVICE_TYPE_PHONE, "");
        //微信
        PlatformConfig.setWeixin(mWXAppID, mWXAppSecret);
        //新浪微博
        PlatformConfig.setSinaWeibo(mWBAppID, mWBAppSecret, "http://sns.whalecloud.com");
        //qq
        PlatformConfig.setQQZone(mQQAppID, mQQAppSecret);
        //支付宝
        PlatformConfig.setAlipay("2015111700822536");

//        WoodUtil.sayHello();

        if (isDebug) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
