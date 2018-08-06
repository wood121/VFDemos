package com.example.wood121.viewdemos.net.mqtt.biz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by het, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 * <p>
 * <p>描述：</p>
 * 名称: 网络判断类<br>
 * 作者: 80010814 4<br>
 * 日期: 2017/12/26<br>
 **/


public class NetWorkStateReceiver extends BroadcastReceiver {

    private final String MQTT_NETWORK_CHANGE="MQTT_NETWORK_CHANGE";
    @Override
    public void onReceive(Context context, Intent intent) {
//        Logc.d("网络状态发生变化");
//        RxManage.getInstance().post(MQTT_NETWORK_CHANGE,MQTT_NETWORK_CHANGE);
//        if (NetworkUtil.isConnected(context)) {
//            /**
//             * 网络状态发送变化，有网时候判断mqtt是否启动，没启动则重新启动
//             */
//            DeviceIotMqttManager.getInstances().reConnectMqtt();
//        }
    }

}


