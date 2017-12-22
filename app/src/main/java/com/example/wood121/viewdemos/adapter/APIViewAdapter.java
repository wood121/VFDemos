package com.example.wood121.viewdemos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.wood121.viewdemos.fragment.APIFragment;
import com.example.wood121.viewdemos.fragment.ViewFragment;

/**
 * Created by wood121 on 2017/12/21.
 */

public class APIViewAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"API", "View"};

    public APIViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return ViewFragment.newInstance();
        }
        return APIFragment.newInstance();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //与TabLayout结合后展示的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
