package com.example.wood121.viewdemos.views.widgets.tablayout.entity;

import java.util.List;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/3/22 16:14<br>
 * 版本： v2.0<br>
 */
public class ResponseBean {
    int code;
    List<DeviceBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DeviceBean> getData() {
        return data;
    }

    public void setData(List<DeviceBean> data) {
        this.data = data;
    }
}
