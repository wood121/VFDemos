package com.example.wood121.viewdemos.views.widgets.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseFragment;
import com.example.wood121.viewdemos.views.widgets.RecyclerView.DividerGridItemDecoration;
import com.example.wood121.viewdemos.views.widgets.tablayout.entity.DeviceBean;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/3/21 15:37<br>
 * 版本： v2.0<br>
 */
public class TabFragment extends BaseFragment {
    private List<DeviceBean> mDeviceList;
    private RecyclerView mRecyclerView;

    public TabFragment() {
    }

    public static TabFragment newInstance(ArrayList<DeviceBean> deviceBeanList) {
        TabFragment fragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("DEVICE_LIST", deviceBeanList);
        fragment.setArguments(bundle);
        return fragment;
    }

//    public static TabFragment getInstance() {
//        return TabFragmentHolder.sTabFragment;
//    }
//
//    static class TabFragmentHolder {
//        private static TabFragment sTabFragment = new TabFragment();
//    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initData(Bundle arguments) {
        Bundle bundle = getArguments();
        if (null != bundle) {
            mDeviceList = (List<DeviceBean>) bundle.getSerializable("DEVICE_LIST");
        }
    }

    @Override
    protected void setListener() {

        mRecyclerView = findViewById(R.id.recyclerview);
        //布局样式
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        //GridLayoutManager的时候，分割线不一样。
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        TabRecAdapter  adapter=  new TabRecAdapter(mDeviceList);
        mRecyclerView.setAdapter(adapter);

    }
}
