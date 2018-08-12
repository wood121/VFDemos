package com.example.wood121.viewdemos.net.hetmqttsdk.bean;

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
public class MqttDataBean<T> {



    private long timestamp;
    private int type;
    private T data;


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }




    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MqttDataBean{" +
                "timestamp=" + timestamp +
                ", type=" + type +
                ", data=" + data +
                '}';
    }
}
