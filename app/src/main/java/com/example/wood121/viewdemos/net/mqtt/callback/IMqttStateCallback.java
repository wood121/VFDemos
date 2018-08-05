package com.example.wood121.viewdemos.net.mqtt.callback;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by het, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 * <p>
 * <p>描述：</p>
 * 名称:  <br>
 * 作者: created by xuchao(80010814) <br>
 * 日期: 2018/3/23<br>
 **/


public interface IMqttStateCallback {
    /**
     * mqtt异常
     * @param code 异常code
     * @param msg  异常msg
     */
  void onError(int code, String msg);
}
