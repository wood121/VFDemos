package com.example.wood121.viewdemos.apis.database_.sqlite_device.bean;

import java.io.Serializable;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2018/11/15 11:11<br>
 * 版本： v2.0<br>
 */
public class VirtualidDeviceBean implements Serializable {
    private String deviceId;
    private String macAddress;
    private String networkDeviceId;
    private int meshId; //正常的0x8002 ~0xfffd
    private int phoneId;//转为 1字节的
    private int virtualId;//两个字节的数据

    public VirtualidDeviceBean() {
    }

    public VirtualidDeviceBean(String deviceId, String macAddress, String networkDeviceId, int meshId, int phoneId, int virtualId) {
        this.deviceId = deviceId;
        this.macAddress = macAddress;
        this.networkDeviceId = networkDeviceId;
        this.meshId = meshId;
        this.phoneId = phoneId;
        this.virtualId = virtualId;
    }

    @Override
    public String toString() {
        return "VirtualidDeviceBean{" +
                "deviceId='" + deviceId + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", networkDeviceId='" + networkDeviceId + '\'' +
                ", meshId=" + meshId +
                ", phoneId=" + phoneId +
                ", virtualId=" + virtualId +
                '}';
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getNetworkDeviceId() {
        return networkDeviceId;
    }

    public void setNetworkDeviceId(String networkDeviceId) {
        this.networkDeviceId = networkDeviceId;
    }

    public int getMeshId() {
        return meshId;
    }

    public void setMeshId(int meshId) {
        this.meshId = meshId;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public int getVirtualId() {
        return virtualId;
    }

    public void setVirtualId(int virtualId) {
        this.virtualId = virtualId;
    }
}
