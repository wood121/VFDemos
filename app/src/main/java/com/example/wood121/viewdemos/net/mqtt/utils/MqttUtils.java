package com.example.wood121.viewdemos.net.mqtt.utils;

import android.text.TextUtils;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by het, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 * <p>mqtt工具类
 * <p>描述：</p>
 * 名称:  <br>
 * 作者: 80010814 4<br>
 * 日期: 2017/6/21<br>
 **/


public class MqttUtils {

    /**
     * 取clientId中间8位,奇数减1位
     * @param clientid
     * @return
     */
    public static String getPassword(String clientid){
        if (TextUtils.isEmpty(clientid)){
            return  null;
        }
        int len=clientid.length();
        String password=null;
        if (len>8){
            int middle;
            if (len%2==1){
                middle=(len-1)/2;
            }else {
                middle=len/2;
            }
            String curString=clientid.substring(middle-4,middle+4);
            password= curString;
        }
        return  password;


    }
}
