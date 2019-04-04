package com.example.wood121.viewdemos.views.widgets_self.model;

import java.io.Serializable;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/4/2 11:16<br>
 * 版本： v2.0<br>
 */
public class BarrageListModel implements Serializable {
    private String sysBarrageMsg;
    private String color;

    public BarrageListModel(String sysBarrageMsg, String color) {
        this.sysBarrageMsg = sysBarrageMsg;
        this.color = color;
    }   

    @Override
    public String toString() {
        return "BarrageListBean{" +
                "sysBarrageMsg='" + sysBarrageMsg + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getSysBarrageMsg() {
        return sysBarrageMsg;
    }

    public void setSysBarrageMsg(String sysBarrageMsg) {
        this.sysBarrageMsg = sysBarrageMsg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
