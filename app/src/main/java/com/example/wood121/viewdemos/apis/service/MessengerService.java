package com.example.wood121.viewdemos.apis.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MessengerService extends Service {
    private static final String TAG = MessengerService.class.getSimpleName() + " :";
//    private static final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    public MessengerService() {
//    }
//
//    @Override
//    public synchronized ComponentName startForegroundServiceAsUser(Intent service, UserHandle user) {
//        return null;
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return mMessenger.getBinder();
//    }
//
//    private static class MessengerHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 101:
//                    //接收的来自客户端的问候
//                    Log.e("wood121", TAG + "receive msg from client:" + msg.getData().get("msg"));
//
//                    //服务端给客户端回复一条消息
//                    Messenger client = msg.replyTo;
//                    Message msgService = Message.obtain(null, 102);
//                    Bundle bundlerService = new Bundle();
//                    bundlerService.putString("reply", "嗯，你的消息我已经收到了，稍后会回复你");
//                    msgService.setData(bundlerService);
//                    try {
//                        client.send(msgService);
//                        mMessenger.send(msgService);
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case 102:
//                    Log.e("wood121", TAG + msg.getData().get("reply"));
//                    break;
//            }
//            super.handleMessage(msg);
//        }
//    }
}
