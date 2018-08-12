package com.example.wood121.viewdemos.net.hetmqttsdk.biz;

import android.content.Context;

import com.example.wood121.viewdemos.net.hetmqttsdk.bean.MqttConnBean;
import com.example.wood121.viewdemos.net.hetmqttsdk.callback.IMqttStateCallback;
import com.example.wood121.viewdemos.net.hetmqttsdk.constants.HetMqttConstant;
import com.example.wood121.viewdemos.net.hetmqttsdk.utils.MqttUtils;

import java.util.HashMap;

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


public class DeviceIotMqttManager {
    private final String TAG=DeviceIotMqttManager.class.getSimpleName();
    private static DeviceIotMqttManager instances=null;
    private Context mContext;
    public HashMap<String,String> deviceBeanHashMap=new HashMap<>();
    private MqttConnBean curMqttConnBea ;
    /**
     * 以秒为单位，定义服务器端从客户端接收消息的最大时间间隔
     */
    private final long keepAliveTime=100;
    /**
     * 不保存连接状态
     */
    private final  int sessionStates=1;
    /**
     * 订阅后台消息开关
     */
    private boolean unSubServerFlag=false;

    /**
     * 是否初始化启动mqtt服务
     */
    private boolean isInit=false;

    private IMqttStateCallback iMqttStateCallback;
    private DeviceIotMqttManager(){

    }

    public static DeviceIotMqttManager getInstances(){
        if (instances==null){
            synchronized (DeviceIotMqttManager.class){
                instances=new DeviceIotMqttManager();
            }
        }
        return  instances;
    }




    /**
     * 初始化
     * @param mContext
     */
    public void init(Context mContext){
        if (!isInit){
            registerRxMsg();
            this.mContext=mContext.getApplicationContext();
            connectMqtt();
            isInit=true;
        }

    }

    public void destroy(){
        if (isInit){
            unRegisterRxMsg();
            MqttConnManager.getInstances().stop();
            isInit=false;
        }

    }

    /**
     * 增加该设备监听
     * @param deviceId
     */
    public void addDeviceListener(String deviceId,String type){
      if (isInit){
          if (deviceBeanHashMap.isEmpty()){
              dealSubType("0",type);
          }
          String key=deviceId+type;
          if (deviceBeanHashMap!=null){
              if (!deviceBeanHashMap.containsKey(key)){
                  deviceBeanHashMap.put(key,key);
              }
          }
          reConnectMqtt();
      }


    }


    /**
     * 增加该设备监听
     * @param deviceId
     */
    public void addDeviceListener(String deviceId,String type, IMqttStateCallback  iMqttStateCallback){
        if (isInit){
            this.iMqttStateCallback=iMqttStateCallback;
            if (deviceBeanHashMap.isEmpty()){
                dealSubType("0",type);
            }
            String key=deviceId+type;
            if (deviceBeanHashMap!=null){
                if (!deviceBeanHashMap.containsKey(key)){
                    deviceBeanHashMap.put(key,key);
                }
            }
            reConnectMqtt();
        }


    }



    /**
     * 删除该设备监听
     * @param deviceId
     */
    public void delDeviceListener(String deviceId,String type){
        if (isInit){
            String key=deviceId+type;
            if (deviceBeanHashMap!=null){
                if (deviceBeanHashMap.containsKey(key)){
                    deviceBeanHashMap.remove(key);
                }
            }
            if (deviceBeanHashMap.isEmpty()){
                dealSubType("1",type);
            }
        }


    }

    /**
     * 开始接受后台数据
     * @param isPush 0接受 1不接受
     * @param type
     */
    private void dealSubType(String isPush,String type){
        if (type.equals(HetMqttConstant.TYPE_DEVICE_CONTROL)){
            String transferType="2,3,4,5,7";
            transferRequire(isPush,transferType);

        }else if(type.equals(HetMqttConstant.TYPE_DEVICE_BIND_STAUTS)){
            String transferType="1";
            transferRequire(isPush,transferType);

        }else if(type.equals(HetMqttConstant.TYPE_DEVICE_ROM_UPDATE_PROGESS)){
            String transferType="6";
            transferRequire(isPush,transferType);

        }
    }

    /**
     * 接收该类型消息
     * @param isPush
     * @param transferType
     */
    private void  transferRequire(String isPush,String transferType ){

//        MqttApi.getInstance().registerTransferRequire(isPush,transferType).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                  if (!TextUtils.isEmpty(s)){
//                      Logc.d(TAG,s);
//                  }
//
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//
//                if (throwable instanceof ApiException){
//                    Logc.e(TAG,throwable.toString());
//                    if (Integer.parseInt(isPush)==0){
//                        if (iMqttStateCallback!=null){
//                            iMqttStateCallback.onError(HetMqttConstant.MQTT_ERROR_RECEIVE_MSG,throwable.toString());
//                        }
//                    }
//
//                }else {
//                    Logc.e(TAG,"mqtt transferRequire failed");
//
//                }
//            }
//        });

    }

    /**
     * 监听登录等出消息
     */
    private void registerRxMsg() {
//        RxManage.getInstance().register(HetMqttConstant.LOGIN_SUCCESS, o -> {
//            //登录成功
//           connectMqtt();
//        });
//        RxManage.getInstance().register(HetMqttConstant.LOGOUT_SUCCESS, o -> {
//            //登出成功
//            Logc.e(TAG+"mqtt","LOGOUT_SUCCESS");
//            MqttConnManager.getInstances().stop();
//        });
//        RxManage.getInstance().register(HetMqttConstant.LOGINOUT, o -> {
//            //异地登录
//            Logc.e(TAG+"mqtt","LOGINOUT");
//            MqttConnManager.getInstances().stop();
//        });
    }

    private void unRegisterRxMsg() {
//        RxManage.getInstance().unregister(HetMqttConstant.LOGIN_SUCCESS);
//        RxManage.getInstance().unregister(HetMqttConstant.LOGOUT_SUCCESS);
//        RxManage.getInstance().unregister(HetMqttConstant.LOGINOUT);
    }

    /**
     * 判断mqtt是否已连接，重连mqtt服务
     */
    public void reConnectMqtt(){
//        if (isInit&&!MqttConnManager.getInstances().isConnectFlag()){
//            if (TokenManager.getInstance().isLogin()){
//                if (curMqttConnBea!=null){
//                    connectMqttServer(curMqttConnBea);
//                }else {
//                    getMqttConfig();
//                }
//
//            }
//        }
    }

    /**
     * 判断mqtt是否已连接，重连mqtt服务
     */
    public void connectMqtt(){
//        if (isInit&&(!MqttConnManager.getInstances().isConnectFlag())){
//            if (TokenManager.getInstance().isLogin()){
//                getMqttConfig();
//            }
//        }
    }


    /**
     * 判断mqtt是否已连接，重连mqtt服务
     */
    public boolean isConnectMqtt(){
       return MqttConnManager.getInstances().isConnectFlag();
    }

    /**
     * 启动mqtt服务
     */
    public void getMqttConfig(){
//        MqttApi.getInstance().getConfig().subscribe(mqttConnBean -> {
//            if (mqttConnBean!=null){
//                dealMqttData(mqttConnBean);
//            }else {
//                if (iMqttStateCallback!=null){
//                    iMqttStateCallback.onError(HetMqttConstant.MQTT_ERROR_GET_CONFIG,"get config error");
//                }
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                if (throwable instanceof ApiException){
//                    if (iMqttStateCallback!=null){
//                        iMqttStateCallback.onError(HetMqttConstant.MQTT_ERROR_GET_CONFIG,throwable.toString());
//                    }
//                    Logc.e(TAG,throwable.toString());
//                }else {
//                    Logc.e(TAG,"mqtt get config failed");
//                }
//            }
//        });


    }



    /**
     * 启动mqtt服务
     * @param mqttConnBean
     */
    private void dealMqttData(MqttConnBean mqttConnBean){
        MqttConnBean mqttConnBeanFull=processMqtt(mqttConnBean);
        if (mqttConnBeanFull!=null){
            curMqttConnBea=mqttConnBeanFull;
            connectMqttServer(curMqttConnBea);
        }
    }

    /**
     * 连接mqtt服务器
     * @param mqttConnBean
     */
    private void connectMqttServer(MqttConnBean mqttConnBean){
//        MqttConnManager.getInstances().start(mContext,mqttConnBean,iMqttStateCallback);
    }



    /**
     * 生成完整的mqtt请求参数
     * @param mqttConnBean
     * @return
     */
    private MqttConnBean processMqtt(MqttConnBean mqttConnBean) {
        try {
            MqttConnBean mqttConnBeanData = new MqttConnBean();
            String clientId = mqttConnBean.getClientId();
            mqttConnBeanData.setClientId(clientId);
            mqttConnBeanData.setPassword(MqttUtils.getPassword(clientId));
            mqttConnBeanData.setUserName(mqttConnBean.getUserName());
            mqttConnBeanData.setProtocolVersion(4);
            mqttConnBeanData.setKeepAlive(keepAliveTime);
            mqttConnBeanData.setRetain(0);
            mqttConnBeanData.setQos(1);
            mqttConnBeanData.setCleanSession(sessionStates);
            //设置主题
            mqttConnBeanData.setTopic(mqttConnBean.getTopic());
            //设置请求地址
            mqttConnBeanData.setBrokerUrl(mqttConnBean.getBrokerUrl());
            return mqttConnBeanData;
        } catch (Exception e) {
//            Logc.e(TAG,e.toString());
            return null;
        }

    }







}
