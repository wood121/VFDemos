package com.example.wood121.viewdemos.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MyMessengerService extends Service {

    //收到消息进行处理
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Bundle data = msg.getData();
                Log.e("url", "MyMessengerService: " + data.get("string"));

                //得到消息中的Messenger对象
                Messenger replyMessenger = msg.replyTo;

                Message message = Message.obtain();
                message.what = 2;
                Bundle bundle = new Bundle();
                bundle.putString("string", "hi,activity");
                message.setData(bundle);

                try {
                    //使用Messenger给Activity发送消息
                    replyMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    private Messenger messenger = new Messenger(mHandler);

    public MyMessengerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();   //给返回
    }


}
