package com.example.wood121.viewdemos.net.mqtt.biz;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by het, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 * <p>
 * <p>描述：</p>
 * 名称:  <br>
 * 作者: 80010814 4<br>
 * 日期: 2017/7/1<br>
 **/


import android.content.ContextWrapper;
import android.text.TextUtils;

import com.example.wood121.viewdemos.net.mqtt.bean.MqttDataBean;
import com.example.wood121.viewdemos.net.mqtt.bean.MqttMsgData;
import com.example.wood121.viewdemos.net.mqtt.constants.HetMqttConstant;
import com.google.gson.JsonSyntaxException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by het, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 * <p>
 * <p>描述：mqtt消息回调类</p>
 * 名称:  <br>
 * 作者: 80010814 4<br>
 * 日期: 2017/6/14<br>
 **/
public class HetMqttCallback implements MqttCallback {
    private final String TAG = HetMqttCallback.class.getSimpleName();
    private ContextWrapper context;
    private String topic;
    private final String TYPE = "type";
    private final String DATA = "data";
    private final String DEVICEID = "deviceId";

    public HetMqttCallback(String topic) {
        // this.context = context;
        this.topic = topic;
    }

    @Override
    public void connectionLost(Throwable cause) {
        if (cause != null) {
//            Logc.e(TAG, cause.toString());
            reConnect();
        }

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        if (message != null) {
            dealMessage(message.toString());
        }

    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {


    }

    /**
     * 处理收到的mqtt消息
     *messageArrived:{"data":{"deviceId":"8B678E4A0E6F65BBAFD35FD06C58D427","type":7},"timestamp":1515072950584,"type":1}
     * @param message
     */
    private void dealMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
//            Logc.d(TAG+"mqtt receive data" + message);
            if (message.contains(TYPE)) {
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(message);
                    MqttDataBean<Object> mqttDataBean = new MqttDataBean<>();
                    if (jsonObject.has(TYPE)) {
                        int type = jsonObject.getInt(TYPE);
                        if (type == HetMqttConstant.MQTT_DEVICE_DATA_TYPE) {
                            //设备控制数据相关
                            if (jsonObject.has(DATA)) {
                                JSONObject jsonObject1 = (JSONObject) jsonObject.get(DATA);
                                if (jsonObject1.has(DEVICEID)) {
                                    String deviceId = jsonObject1.getString(DEVICEID);
                                    if (deviceId != null) {
                                        HashMap<String, String> deviceBeanHashMap = DeviceIotMqttManager.getInstances().deviceBeanHashMap;
                                        if (deviceBeanHashMap != null) {
                                            if (deviceBeanHashMap.containsKey(deviceId + HetMqttConstant.TYPE_DEVICE_CONTROL)) {
                                                //设备控制数据
                                                String topic = deviceBeanHashMap.get(deviceId + HetMqttConstant.TYPE_DEVICE_CONTROL);
                                                if (!TextUtils.isEmpty(topic)) {
                                                    if (jsonObject1.has(TYPE)){
                                                        int code = jsonObject1.getInt(TYPE);
                                                        String jsonData=null;
                                                        if (jsonObject1.has(DATA)){
                                                             jsonData = jsonObject1.getJSONObject(DATA).toString();
                                                        }
                                                        MqttMsgData mqttDataEvent = new MqttMsgData();
                                                        if (code == HetMqttConstant.MQTT_DEVICE_DATA_TYPE_CONFIG) {
                                                            //控制数据
                                                            mqttDataEvent.setJsonData(jsonData);
                                                            mqttDataEvent.setCode(HetMqttConstant.DEVICE_MQTT_CONFIG_DATA);
                                                        } else if (code == HetMqttConstant.MQTT_DEVICE_DATA_TYPE_RUN) {
                                                            //运行数据
                                                            mqttDataEvent.setJsonData(jsonData);
                                                            mqttDataEvent.setCode(HetMqttConstant.DEVICE_MQTT_RUN_DATA);
                                                        } else if (code == HetMqttConstant.MQTT_DEVICE_DATA_TYPE_ERROR) {
                                                            //异常数据
                                                            mqttDataEvent.setJsonData(jsonData);
                                                            mqttDataEvent.setCode(HetMqttConstant.DEVICE_MQTT_ERROR_DATA);
                                                        } else if (code == HetMqttConstant.MQTT_DEVICE_DATA_TYPE_ONLINE) {
                                                            //设备在线
                                                            String onlineStatus = "onlineStatus=1";
                                                            mqttDataEvent.setJsonData(onlineStatus);
                                                            mqttDataEvent.setCode(HetMqttConstant.DEVICE_MQTT_ONLINE_STATUS);
                                                        } else if (code == HetMqttConstant.MQTT_DEVICE_DATA_TYPE_UN_ONLINE) {
                                                            //设备不在线
                                                            String onlineStatus = "onlineStatus=2";
                                                            mqttDataEvent.setJsonData(onlineStatus);
                                                            mqttDataEvent.setCode(HetMqttConstant.DEVICE_MQTT_ONLINE_STATUS);
                                                        }
//                                                        Logc.d(TAG+"mqtt send rxmag" + mqttDataEvent.toString());
//                                                        RxManage.getInstance().post(topic, mqttDataEvent);
                                                    }


                                                }
                                            }


                                        } else if (DeviceIotMqttManager.getInstances().deviceBeanHashMap.containsKey(deviceId + HetMqttConstant.TYPE_DEVICE_BIND_STAUTS)) {
                                            //设备绑定数据
                                        } else if (DeviceIotMqttManager.getInstances().deviceBeanHashMap.containsKey(deviceId + HetMqttConstant.TYPE_DEVICE_ROM_UPDATE_PROGESS)) {
                                            //设备升级数据
                                        } else {

                                        }
                                    }

                                }
                            }

                        }

                    }
                } catch (JsonSyntaxException e) {
//                    Logc.e(TAG, e.toString());
                } catch (JSONException e) {
//                    Logc.e(TAG, e.toString());
                }
            }


        }
    }




    /**
     * 重连mqtt
     */
    public  void reConnect(){
//        MqttApi.getInstance().getConfig().subscribe(mqttConnBean -> {
//            MqttConnManager.getInstances().scheduleReconnect();
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//
//            }
//        });
    }
}
