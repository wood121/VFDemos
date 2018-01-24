package com.example.wood121.viewdemos.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wood121.viewdemos.BaseActivity;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.service.MyService;

import butterknife.BindView;
import butterknife.OnClick;

public class ServiceActivity extends BaseActivity {

    @BindView(R.id.start_service)
    Button startService;
    @BindView(R.id.stop_service)
    Button stopService;
    @BindView(R.id.bind_service)
    Button bindService;
    @BindView(R.id.unbind_service)
    Button unbindService;
    @BindView(R.id.messenger)
    Button messengerBtn;

    //MyServier：本地、远程Service
//    private ServiceConnection serviceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
////            ((MyService.MyBinder) iBinder).startDownload();
//            MyAIDLService myAIDLService = MyAIDLService.Stub.asInterface(iBinder);
//            try {
//                myAIDLService.toUpperCase("呵呵");
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName componentName) {
//
//        }
//    };


    private ServiceConnection messConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private Messenger messenger;

    @OnClick({R.id.start_service, R.id.stop_service, R.id.bind_service, R.id.unbind_service, R.id.messenger})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, MyService.class);
        switch (view.getId()) {
            case R.id.start_service:
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(intent);
                break;
            case R.id.bind_service:
                bindService(intent, messConnection, BIND_AUTO_CREATE);
//                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(messConnection);
//                unbindService(serviceConnection);
                break;
            case R.id.messenger:
                sayHi2Service();
                break;
        }
    }

    private void sayHi2Service() {
        Message msg = Message.obtain();
        msg.what = 1;

        Bundle bundle = new Bundle();
        bundle.putString("string", "hi,service");
        msg.setData(bundle);
        msg.replyTo = new Messenger(mHandler);

        try {
            messenger.send(msg);    //Service中获取到的Messenger对象进行信息传递
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                Bundle data = msg.getData();
                Log.e("url", "ServiceActivity" + data.get("string"));
            }
        }
    };

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_service;
    }

    @Override
    protected void initPageViewListener() {

    }

}
