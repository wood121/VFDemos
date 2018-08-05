package com.example.wood121.viewdemos.net.mqtt.constants;

/**
 * 控制相关常量
 */
public interface HetMqttConstant {

    String LOGIN_SUCCESS = "login_success";  //控制数据
    String LOGOUT_SUCCESS = "logout_success";  //控制数据
    String LOGINOUT= "loginout";  //控制数据

    int DEVICE_MQTT_CONFIG_DATA = 100;  //控制数据
    int DEVICE_MQTT_RUN_DATA = 101;    //运行数据
    int DEVICE_MQTT_ERROR_DATA = 102;   //异常数据
    int DEVICE_MQTT_ONLINE_STATUS = 110;  //在线状态

    int MQTT_DEVICE_DATA_TYPE = 1;  //设备类型数据
    int MQTT_DEVICE_DATA_TYPE_CONFIG = 2;  //设备控制数据
    int MQTT_DEVICE_DATA_TYPE_RUN = 3;  //设备运行数据
    int MQTT_DEVICE_DATA_TYPE_ERROR = 4;  //设备异常数据
    int MQTT_DEVICE_DATA_TYPE_ONLINE = 5;  //设备在线数据
    int MQTT_DEVICE_DATA_TYPE_UN_ONLINE = 7;  //设备离线数据

    String TYPE_DEVICE_CONTROL = "TYPE_DEVICE_CONTROL";  //控制数据
    String TYPE_DEVICE_BIND_STAUTS = "TYPE_DEVICE_BIND_STAUTS";    //绑定查询进度
    String TYPE_DEVICE_ROM_UPDATE_PROGESS = "TYPE_DEVICE_ROM_UPDATE_PROGESS";   //设备固件升级进度

    int MQTT_ERROR_CONNECT_FAILED= 10001;  //mqtt无法连接
    int MQTT_ERROR_GET_CONFIG= 10002;  //mqtt获取配置失败
    int MQTT_ERROR_RECEIVE_MSG= 10003;  //mqtt接收消息失败
    int MQTT_ERROR_MQTT_MSG= 10004;  //mqtt接收消息失败

}
