package com.example.wood121.viewdemos.views.widgets.tablayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

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

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTabs = new ArrayList<>();
        mFragments = new ArrayList<>();

        //fragments view的初始化
        TabFragment tfHaHa = new TabFragment();
        TabFragment tfHeHe = new TabFragment();
        TabFragment tfHei = new TabFragment();
        mFragments.add(tfHaHa);
        mFragments.add(tfHeHe);
        mFragments.add(tfHei);

        //tabs数据
        mTabs.add("哈哈");
        mTabs.add("呵呵");
        mTabs.add("嘿嘿");
        //fragments需要显示的内容
        tfHaHa.setData(this, mTabs.get(0));
        tfHeHe.setData(this, mTabs.get(1));
        tfHei.setData(this, mTabs.get(2));
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
//        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        tabViewPagerAdapter.setData(this, mTabs, mFragments);
        mViewPager.setAdapter(tabViewPagerAdapter);
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
