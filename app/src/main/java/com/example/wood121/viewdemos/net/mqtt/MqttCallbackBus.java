package com.example.wood121.viewdemos.net.mqtt;

import android.content.Context;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author wood121
 * @desc
 * @time:2018/8/4
 */
public class MqttCallbackBus implements MqttCallback {

    public MqttCallbackBus(Context context) {

    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
