package com.example.wood121.viewdemos.net.mqtt;

import android.content.Context;

/**
 * @author wood121
 * @desc
 * @time:2018/8/4
 */
public class MqttManager {

    private final Context mContext;
    private final MqttCallbackBus mMqttCallbackBus;
    private static MqttManager mInstance = null;

    private MqttManager(Context context) {
        mMqttCallbackBus = new MqttCallbackBus(context);
        this.mContext = context;
    }

    public static MqttManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MqttManager.class) {
                if (mInstance == null) {
                    mInstance = new MqttManager(context);
                }
            }
        }
        return mInstance;
    }


    public void createConnect(String base_mqt_url, String baseMqttUsename, String baseMqttPassword) {

    }
}
