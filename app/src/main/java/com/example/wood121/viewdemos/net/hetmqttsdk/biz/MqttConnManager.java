package com.example.wood121.viewdemos.net.hetmqttsdk.biz;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.wood121.viewdemos.net.hetmqttsdk.bean.MqttConnBean;
import com.example.wood121.viewdemos.net.hetmqttsdk.callback.IMqttStateCallback;
import com.example.wood121.viewdemos.net.hetmqttsdk.constants.HetMqttConstant;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;


/**
 * ————————————————————————————————
 * Copyright (C) 2014-2017, by het, Shenzhen, All rights reserved.
 * ————————————————————————————————
 * <p>MqttConnManage
 * <p>描述：</p>
 * 名称:  <br>
 * 作者: uuxia<br>
 * 版本: 1.0<br>
 * 日期: 2017/5/11 20:09<br>
 **/
public class MqttConnManager {

    public static final String TAG = MqttConnManager.class.getSimpleName();
    /**
     * mqtt连接超时时间
     */
    private final int MQTT_CONNECT_TIMEOUT_TIME=20;
    /**
     * 心跳包时间
     */
    private final int MQTT_HEART_TIME=30;
    private MqttAndroidClient client = null;
    private MqttConnectOptions conOpt;
    private MqttConnBean connBean;
    //是否连接
    public volatile boolean isConnectFlag = false;
    //断开连接标志
    private boolean cancleConnectFlag = false;
    private Context mContext;
    private static MqttConnManager instances = null;
    private IMqttStateCallback iMqttStateCallback;
    //重连次数,默认重连两次
    private int  retryReconnecTime =2;


    //重连间隔时间
    private static final Integer MILLIS_IN_ONE_SECOND = 1000*3;
    public static MqttConnManager getInstances() {
        if (instances == null) {
            synchronized (MqttConnManager.class) {
                instances = new MqttConnManager();
            }
        }
        return instances;
    }


    /**
     * 订阅主题
     */
    public void subscribe() {
        try {
            // 订阅myTopic主题
            if (connBean!=null){
                String topic = connBean.getTopic();
                int qas = connBean.getQos();
                if (!TextUtils.isEmpty(topic)) {
                    client.subscribe(topic, qas);
                }
            }
        } catch (MqttException e) {
//            Logc.e(TAG, e.toString());
        }
    }

    /**
     * 是否已连上mqtt服务器
     * @return
     */
    public boolean isConnectFlag() {
        if (client!=null&&client.isConnected()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 启动mqtt服务
     * @param mContext
     * @param bean
     */
    public void start(Context mContext, MqttConnBean bean,IMqttStateCallback iMqttStateCallback) {
        this.connBean = bean;
        this.mContext = mContext;
//        Logc.d(TAG,"mqtt init!");
        this.iMqttStateCallback=iMqttStateCallback;
        init();
    }

    /**
     * 断开mqtt连接服务
     */
    public synchronized void  stop() {
        if (client != null) {
            try {
                client.disconnect();
                client=null;
//                Logc.d(TAG,"mqtt server close");
            } catch (MqttException e) {
//                Logc.e(TAG,e.toString());
            }
            if (isConnectFlag) {
                isConnectFlag=false;
            }
        }


    }

    /**
     * 初始化mqtt相关配置
     */
    private void init() {
        // 服务器地址（协议+地址+端口号）
        isConnectFlag = false;
        String uri = connBean.getBrokerUrl();
        retryReconnecTime=2;
        if (!TextUtils.isEmpty(uri)) {
            if (client == null) {
                client = new MqttAndroidClient(mContext, uri, connBean.getClientId());
                // 设置MQTT监听并且接受消息
                if (!TextUtils.isEmpty(connBean.getTopic())){
                    client.setCallback(new HetMqttCallback(connBean.getTopic()));
                    conOpt = new MqttConnectOptions();
                    // 清除缓存
                    conOpt.setCleanSession(true);
                    // 设置超时时间，单位：秒
                    conOpt.setConnectionTimeout(MQTT_CONNECT_TIMEOUT_TIME);
                   // 心跳包发送间隔，单位：秒
                    conOpt.setKeepAliveInterval(MQTT_HEART_TIME);
                    // 用户名
                    conOpt.setUserName(connBean.getUserName());
                    // 密码
                    conOpt.setPassword(connBean.getPassword().toCharArray());
                    doClientConnection();
                }else {
                    if (iMqttStateCallback!=null){
                        iMqttStateCallback.onError(HetMqttConstant.MQTT_ERROR_MQTT_MSG,"topic is null");
                    }
                }


            }else {
                //若mqtt服务不为空，并且未连接，则重新连接
                if (!client.isConnected()){
                    doClientConnection();
                }
            }

        }


    }



    /**
     * 连接MQTT服务器
     */
    private void doClientConnection() {
//        if (client!=null&&!client.isConnected() && NetworkUtil.isConnected(mContext)) {
            try {
                client.connect(conOpt, null, iMqttActionListener);
            } catch (MqttException e) {
//                Logc.e(TAG, e.toString());
            }
//        }

    }



    // MQTT是否连接成功
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {

        @Override
        public void onSuccess(IMqttToken arg0) {
            if (client!=null&&client.isConnected()){
                if (!isConnectFlag) {
                    isConnectFlag = true;
                }
//                Logc.d(TAG,"mqtt server start");
                subscribe();
            }
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable arg1) {
//            Logc.e(TAG, "mqtt connect " + arg1.getMessage());
            if (isConnectFlag){
                isConnectFlag=false;
            }
            scheduleReconnect();
            if (retryReconnecTime==0){
                if (iMqttStateCallback!=null){
                    iMqttStateCallback.onError(HetMqttConstant.MQTT_ERROR_CONNECT_FAILED,"mqtt connect failed");
                }
            }
        }
    };


    /**
     * Schedule an auto-reconnect attempt using backoff logic.
     *
     * @return true if attempt was scheduled, false otherwise.
     */
    void scheduleReconnect() {
        // schedule a reconnect if unlimited or if we haven't yet hit the limit

             if (retryReconnecTime>0){
                 (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
                     @Override
                     public void run() {
//                         Logc.d(TAG,"mqtt reconnect"+retryReconnecTime);
                         retryReconnecTime--;
                         doClientConnection();
                     }
                 }, MILLIS_IN_ONE_SECOND );

             }




    }






}
