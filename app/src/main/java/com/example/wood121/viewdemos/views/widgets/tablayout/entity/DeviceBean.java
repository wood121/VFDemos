package com.example.wood121.viewdemos.views.widgets.tablayout.entity;

import java.io.Serializable;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/3/22 16:15<br>
 * 版本： v2.0<br>
 */
public class DeviceBean implements Serializable {
    /**
     * {
     * "keyRanage":5089,
     * "productIcon":"http://fileserver1.clife.net:8080/group1/M00/9C/8F/CvtlhltkAfeAPYptAAA03jplURE8789041",
     * "productName":"冰箱",
     * "roomId":1,
     * "roomName":"客厅"
     * }
     */
    private int keyRanage;
    private String productIcon;
    private String productName;
    private int roomId;
    private String roomName;

    public DeviceBean(int keyRange, String productIcon, String productName, int roomId, String roomName) {
        this.keyRanage = keyRange;
        this.productIcon = productIcon;
        this.productName = productName;
        this.roomId = roomId;
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "DeviceBean{" +
                "keyRanage=" + keyRanage +
                ", productIcon='" + productIcon + '\'' +
                ", productName='" + productName + '\'' +
                ", roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                '}';
    }

    public int getKeyRanage() {
        return keyRanage;
    }

    public void setKeyRanage(int keyRanage) {
        this.keyRanage = keyRanage;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//
//    }
}
