package com.example.wood121.viewdemos.views.widgets.RecyclerView.recitem;

import java.io.Serializable;
import java.util.List;

/**
 * @date: 2019/7/27
 * @version:
 * @author: liuzhengling
 * @des:
 */
public class GroupListBean implements Serializable {
    private List<ChildListBean> childList;

    private String groupName;

    public List<ChildListBean> getChildList() {
        return childList;
    }

    public void setChildList(List<ChildListBean> childList) {
        this.childList = childList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
