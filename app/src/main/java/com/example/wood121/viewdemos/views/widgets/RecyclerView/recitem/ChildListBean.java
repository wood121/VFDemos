package com.example.wood121.viewdemos.views.widgets.RecyclerView.recitem;

import java.io.Serializable;

/**
 * @date: 2019/7/27
 * @version:
 * @author: liuzhengling
 * @des:
 */
public class ChildListBean implements Serializable {
    /**
     * {
     * "childName":"组员1",
     * "openTime":"2017-07-06 10:05:16"
     * },
     */

    private String childName;
    private String openTime;
    private boolean isGroup;

    public ChildListBean(String childName, boolean isGroup) {
        this.childName = childName;
        this.isGroup = isGroup;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }


}
