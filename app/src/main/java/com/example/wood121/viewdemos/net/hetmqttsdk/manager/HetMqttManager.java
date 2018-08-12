package com.example.wood121.viewdemos.net.hetmqttsdk.manager;

import android.content.Context;

import com.example.wood121.viewdemos.net.hetmqttsdk.bean.MqttConnBean;
import com.example.wood121.viewdemos.net.hetmqttsdk.biz.DeviceIotMqttManager;
import com.example.wood121.viewdemos.net.hetmqttsdk.callback.IMqttStateCallback;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by het, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 * <p>
 * <p>描述：mqtt管理类</p>
 * 名称:  <br>
 * 作者: 80010814 4<br>
 * 日期: 2017/6/14<br>
 **/


public class HetMqttManager {
    private final String TAG=HetMqttManager.class.getSimpleName();
    private static HetMqttManager instances=null;
    private Context mContext;
    private MqttConnBean curMqttConnBean;

    private HetMqttManager(){

    }

    public static HetMqttManager getInstances(){
        if (instances==null){
            synchronized (HetMqttManager.class){
                instances=new HetMqttManager();
            }
        }
        return  instances;
    }


    /**
     * 初始化mqtt服务
     * @param mContext
     */
    public void init(Context mContext){
        //必须在application里面初始化，否则会报android.app.ServiceConnectionLeaked was originally bound 异常
        DeviceIotMqttManager.getInstances().init(mContext);
    }
    /**
     * 关闭qtt服务
     */
    public void destroy(){
        DeviceIotMqttManager.getInstances().destroy();
    }

    /**
     * 订阅指定设备的指定type消息
     * @param deviceId 设备id
     * @param type      消息类型
     */
    public void registerDevice(String deviceId,String type){
        DeviceIotMqttManager.getInstances().addDeviceListener(deviceId,type);

    }

    /**
     * 订阅指定设备的指定type消息
     * @param deviceId 设备id
     * @param type      消息类型
     */
    public void registerDevice(String deviceId, String type, IMqttStateCallback iMqttStateCallback){
        DeviceIotMqttManager.getInstances().addDeviceListener(deviceId,type,iMqttStateCallback);

    }
    /**
     * 取消指定设备的指定type消息
     * @param deviceId 设备id
     * @param type      消息类型
     */
    public void unRegisterDevice(String deviceId,String type){
        DeviceIotMqttManager.getInstances().delDeviceListener(deviceId,type);
    }

    /**
     * 是否已连上mqtt服务器
     * @return true已连上，false未连上
     */
    public boolean isConnectMqtt(){
       return DeviceIotMqttManager.getInstances().isConnectMqtt();
    }








}
