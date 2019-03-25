package com.example.wood121.viewdemos.views.widgets.tablayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.views.widgets.tablayout.entity.DeviceBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * viewpager + fragment，fragment数量与样式
 * fragment中Recyclerview的情况
 */
public class TablayoutActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<String> mTabs;
    private ArrayList<Fragment> mFragments;
    private TabViewPagerAdapter mTabViewPagerAdapter;
    private Handler mHandler;
    private ArrayList<DeviceBean> deviceList = new ArrayList<>();
    private Runnable deviceListRunable = () -> {
        //拿到数据、有tab、tab页对应的list集合
        deviceList.add(new DeviceBean(15, "icon", "电视机", 1, "客厅"));
        deviceList.add(new DeviceBean(25, "icon", "空调", 1, "客厅"));
        deviceList.add(new DeviceBean(45, "icon", "洗衣机", 2, "卫生间"));
        deviceList.add(new DeviceBean(80, "icon", "电饭煲", 3, "厨房"));

        //根据roomId,roomName分类：客厅 --list，卫生间 --list，厨房 --list
        Map<String, List<DeviceBean>> deviceMap = listToMap(deviceList);
        List<String> tabList = getMapKeyList(deviceMap);
        mTabs.clear();
        mTabs.addAll(tabList);

        for (int i = 0; i < tabList.size(); i++) {
            String key = tabList.get(i);
            mFragments.add(TabFragment.newInstance((ArrayList<DeviceBean>) deviceMap.get(key)));
        }
        mTabViewPagerAdapter.setData(this, mTabs, mFragments);
    };

    private List<String> getMapKeyList(Map<String, List<DeviceBean>> deviceMap) {
        ArrayList<String> list = new ArrayList<>();
        Set set = deviceMap.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            list.add((String) it.next());
        }
        return list;
    }

    public Map<String, List<DeviceBean>> listToMap(List<DeviceBean> deviceList) {
        if (null != deviceList && deviceList.size() > 0) {
            Map<String, List<DeviceBean>> deviceMap = new TreeMap<>();
            for (DeviceBean deviceBean :
                    deviceList) {
                String roomName = deviceBean.getRoomName();
                if (deviceMap.containsKey(roomName)) {
                    List<DeviceBean> mapList = deviceMap.get(roomName);
                    mapList.add(deviceBean);
                } else {
                    ArrayList<DeviceBean> mapList = new ArrayList<>();
                    mapList.add(deviceBean);
                    deviceMap.put(roomName, mapList);
                }
            }
            return deviceMap;
        }
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //模拟数据请求,1.5s后拿到数据
        mHandler = new Handler();
        mHandler.postDelayed(deviceListRunable, 500);

        //fragments view的初始化
        mFragments = new ArrayList<>();
        mTabs = new ArrayList<>();

//        TabFragment tfHaHa = new TabFragment();
//        TabFragment tfHeHe = new TabFragment();
//        TabFragment tfHei = new TabFragment();
//        mFragments.add(tfHaHa);
//        mFragments.add(tfHeHe);
//        mFragments.add(tfHei);
//
//        mTabs.add("哈哈");
//        mTabs.add("呵呵");
//        mTabs.add("嘿嘿");
//        //fragments需要显示的内容
//        tfHaHa.setData(this, mTabs.get(0));
//        tfHeHe.setData(this, mTabs.get(1));
//        tfHei.setData(this, mTabs.get(2));
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_tablayout;
    }

    @Override
    protected void initPageViewListener() {
        findViewById(R.id.btn_popwindow).setOnClickListener(this);
        //TabLayout 与 viewPager绑定
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        mTabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        mTabViewPagerAdapter.setData(this, mTabs, mFragments);
        mViewPager.setAdapter(mTabViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_popwindow:

                break;
        }
    }
}
