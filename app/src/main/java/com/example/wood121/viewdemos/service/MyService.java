package com.example.wood121.viewdemos.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.wood121.viewdemos.MyAIDLService;

/**
 * Created by wood121 on 2018/1/12.
 */

public class MyService extends Service {
    public static final String TAG = "MyService";
    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();


        //已经过期的用法
//        Notification notification = new Notification(R.mipmap.ic_launcher,
//                "有通知到来", System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);
//        notification.setLatestEventInfo(this, "这是通知的标题", "这是通知的内容",
//                pendingIntent);
//        startForeground(1, notification);

        Log.e(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 开始执行后台任务
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return stubBinder;
    }

    MyAIDLService.Stub stubBinder = new MyAIDLService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            Log.e("url", "plus");
            return 0;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            Log.e("url", "toUpperCase:" + str);
            return null;
        }
    };

    public class MyBinder extends Binder {

        public void startDownload() {
            Log.e(TAG, "startDownload");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 执行具体的下载任务
                }
            }).start();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
}
