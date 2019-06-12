package com.example.wood121.viewdemos.base.adapter;

import android.util.Log;

import com.example.wood121.viewdemos.apis.APIFragment;
import com.example.wood121.viewdemos.frames.FrameFragment;
import com.example.wood121.viewdemos.jnis.JNIFragment;
import com.example.wood121.viewdemos.math.MathFragment;
import com.example.wood121.viewdemos.sdk_thirdparty.SDKFragment;
import com.example.wood121.viewdemos.views.ViewFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by wood121 on 2017/12/21.
 * APIViewAdapter中创建的数据
 */

public class TabVPAdapter extends FragmentPagerAdapter {

    private ArrayList<String> mList;
    private int mType;

    public TabVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return getAFMAdapter(position);
    }

    /**
     * 子类返回页面内分类的adapter
     *
     * @param position
     * @return
     */
    protected Fragment getAFMAdapter(int position) {
        Log.e("wood121", "getAFMAdapter/" + "type:" + mType);
        switch (mType) {    //在哪个页面的
            case 0: //APIViewFragment旗下的东西
                if (position == 1) {
                    return ViewFragment.newInstance();
                }
                return APIFragment.newInstance();
            case 1:
                if (position == 1) {
                    return SDKFragment.newInstance();
                }
                return FrameFragment.newInstance();
            case 2:
                if (position == 1) {
                    return JNIFragment.newInstance();
                }
                return MathFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    //与TabLayout结合后展示的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }

    public void setData(ArrayList<String> list, int type) {
        this.mType = type;
        this.mList = list;
    }

}
