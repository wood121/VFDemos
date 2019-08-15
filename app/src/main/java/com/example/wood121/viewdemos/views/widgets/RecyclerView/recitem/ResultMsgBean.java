package com.example.wood121.viewdemos.views.widgets.RecyclerView.recitem;

import java.io.Serializable;
import java.util.List;

/**
 * @date: 2019/7/27
 * @version:
 * @author: liuzhengling
 * @des:
 */
public class ResultMsgBean implements Serializable {
    private List<GroupListBean> groupList;

    public List<GroupListBean> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupListBean> groupList) {
        this.groupList = groupList;
    }
}
