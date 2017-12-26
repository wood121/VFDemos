package com.example.wood121.viewdemos.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.wood121.viewdemos.BaseFragment;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.adapter.TabVPAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by wood121 on 2017/12/22.
 * APIViewFragment,FSdkFragment,MBaseFragment的封装
 */

public class BaseTabFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    protected static ArrayList<String> list;
    protected TabVPAdapter afmAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_base_afm;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {
        Log.e("viewPager", viewPager + "");
        //viewpager的设置
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(getViewPagerAdapter());
        //TabLayout绑定ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }

    protected TabVPAdapter getViewPagerAdapter() {
        if (afmAdapter == null) {
            afmAdapter = new TabVPAdapter(getChildFragmentManager());
            /*
            下面这句有毒！！！！在fragment中拿FragmentManager对象需要使用getChildFragmentManager()
            afmAdapter = new TabVPAdapter(mActivity.getSupportFragmentManager());
             */
        }
        if (null == list) {
            list = new ArrayList<>();
        }
        list.clear();   //先清楚其它fragment中赋予的子标题
        getFragDatas();
        Log.e("hashcode", "viewPager:" + viewPager.hashCode() + " list:" + list.size());
        return afmAdapter;
    }

    /**
     * 子类中：list.add（"子标题栏"），afmadapter.setData(list,type)分开不同的类型
     */
    protected void getFragDatas() {

    }

}
