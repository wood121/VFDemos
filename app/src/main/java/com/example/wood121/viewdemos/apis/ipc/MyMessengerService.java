package com.example.wood121.viewdemos.apis.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyMessengerService extends Service {

//    private Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            if (msg.what == 1) {
//                Bundle data = msg.getData();
//                Log.e("url", "MyMessengerService: " + data.get("string"));
//
//                //得到消息中的Messenger对象
//                Messenger replyMessenger = msg.replyTo;
//
//                Message message = Message.obtain();
//                message.what = 2;
//                Bundle bundle = new Bundle();
//                bundle.putString("string", "hi,activity");
//                message.setData(bundle);
//
//                try {
//                    //使用Messenger给Activity发送消息
//                    replyMessenger.send(message);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            return false;
//        }
//    });

//    private Messenger messenger = new Messenger(mHandler);

    public MyMessengerService() {

    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
//        return messenger.getBinder();   //给返回
        return null;
    }


}
