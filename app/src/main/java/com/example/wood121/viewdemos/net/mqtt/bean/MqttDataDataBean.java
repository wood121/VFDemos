package com.example.wood121.viewdemos.net.mqtt.bean;

/**
 * ————————————————————————————————
 * Copyright (C) 2014-2017, by het, Shenzhen, All rights reserved.
 * ————————————————————————————————
 * <p>
 * <p>描述：</p>
 * 名称:  <br>
 * 作者: uuxia<br>
 * 版本: 1.0<br>
 * 日期: 2017/5/12 12:08<br>
 **/
public class MqttDataDataBean<T> {

    private String deviceId;
    private int type;
    private T data;
    public void setType(int type) {
        this.type = type;
    }



    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MqttDataDataBean{" +
                "deviceId=" + deviceId +
                ", type=" + type +
                ", data=" + data +
                '}';
    }
}
