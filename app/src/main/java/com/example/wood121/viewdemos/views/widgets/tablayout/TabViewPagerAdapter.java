package com.example.wood121.viewdemos.views.widgets.tablayout;

import android.content.Context;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/3/22 10:04<br>
 * 版本： v2.0<br>
 */
public class TabViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTabs;

    public void setData(Context context, ArrayList<String> tabs, ArrayList<Fragment> fragments) {
        this.mContext = context;
        this.mTabs = tabs;
        this.mFragments = fragments;
    }

    public TabViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position);
    }

}
