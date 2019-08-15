package com.example.wood121.viewdemos.views.widgets.RecyclerView.recitem;

import android.os.Bundle;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 列表分组
 * 1.数据结构：LinkedHashMap<String, ArrayList<ChildListBean>> groupMap
 * ResultMsgBean,GroupListBean,ChildListBean
 * 2.rec
 */
public class RecItemActivity extends BaseActivity {
    private RecyclerView recyclerView;

    private LinkedHashMap<String, ArrayList<ChildListBean>> groupMap = new LinkedHashMap<>();
    private ArrayList<ChildListBean> mChildList;

    @Override
    protected void initData(Bundle savedInstanceState) {
        String jsonData = "{\"groupList\":[{\"childList\":[{\"childName\":\"组员1\",\"openTime\":\"2017-04-20 11:40:18\"}," +
                "{\"childName\":\"组员2\",\"openTime\":\"2017-04-20 11:40:18\"},{\"childName\":\"组员3\",\"openTime\":\"2017-04-20 11:40:18\"}],\"groupName\":\"分组1\"}," +
                "{\"childList\":[{\"childName\":\"组员1\",\"openTime\":\"2017-07-06 10:05:16\"}," +
                "{\"childName\":\"组员2\",\"openTime\":\"2017-07-06 10:05:16\"}],\"groupName\":\"分组2\"}]}";

        ResultMsgBean bean = new Gson().fromJson(jsonData, ResultMsgBean.class);
        List<GroupListBean> groupList = bean.getGroupList();
        for (int i = 0; i < groupList.size(); i++) {
            mChildList = new ArrayList<>();
            GroupListBean groupListBean = groupList.get(i);
            List<ChildListBean> childList = groupListBean.getChildList();
            for (int j = 0; j < childList.size(); j++) {
                ChildListBean childListBean = childList.get(j);
                mChildList.add(childListBean);
            }
            groupMap.put(groupListBean.getGroupName(), mChildList);
        }
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_rec_item;
    }

    @Override
    protected void initEvent() {
        recyclerView = findViewById(R.id.recview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ItemGroupChildAdapter adapter = new ItemGroupChildAdapter(this);
        adapter.setList(groupMap);
        recyclerView.setAdapter(adapter);
    }
}
