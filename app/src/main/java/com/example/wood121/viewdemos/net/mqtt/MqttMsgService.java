package com.example.wood121.viewdemos.net.mqtt;

import android.app.Service;
import android.content.Intent;
import android.net.Network;
import android.os.IBinder;
import android.util.Log;

import com.example.wood121.viewdemos.net.http.Rx.RxBus;

public class MqttMsgService extends Service {
    public MqttMsgService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("wood121","MqttMsgService 创建了");
        RxBus.getInstance().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("wood121","MqttMsgService 启动了");

        new Thread(new Runnable() {
            @Override
            public void run() {
                connectMqtt();
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    private void connectMqtt() {
        MqttManager.getInstance(this).createConnect(MqttNetwork.Base_MQT_URL,MqttNetwork.BASE_MQTT_USENAME,MqttNetwork.BASE_MQTT_PASSWORD);
        

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
