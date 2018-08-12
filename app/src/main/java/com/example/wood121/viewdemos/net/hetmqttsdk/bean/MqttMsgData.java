package com.example.wood121.viewdemos.net.hetmqttsdk.bean;

/**
 * Created by xuchao on 2016/3/25.
 */
public class MqttMsgData {
    //
    private int code = 0;
    private String jsonData;
    private String errId="";
    private String errMsg="";


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getErrId() {
        return errId;
    }

    public void setErrId(String errId) {
        this.errId = errId;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "MqttMsgData{" +
                "code=" + code +
                ", jsonData='" + jsonData + '\'' +
                ", errId='" + errId + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}

