package com.example.wood121.viewdemos.apis.ipc;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.apis.service.MessengerService;

import androidx.appcompat.app.AppCompatActivity;

public class MessengerActivity extends AppCompatActivity {
    private static final String TAG = MessengerActivity.class.getSimpleName() + " :";
    private Messenger mMService;
    private Messenger mGetReplyMessenger = new Messenger(new MessgerHandler());

    private static class MessgerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 102:
                    Log.e("wood121", TAG + "receiver from server:" + msg.getData().get("reply"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("wood121", TAG + "onServiceConnected");
            //这是客户端得到Binder对象、创建Messenger、而后通过Messenger对象给服务端发消息
            mMService = new Messenger(service);
            Message msg = Message.obtain(null, 101);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "hello,this is client");
            msg.setData(bundle);
            msg.replyTo = mGetReplyMessenger;
            try {
                mMService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
