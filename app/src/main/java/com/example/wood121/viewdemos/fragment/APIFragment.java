package com.example.wood121.viewdemos.fragment;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.activity.BleAcitivty;
import com.example.wood121.viewdemos.activity.VideoActivity;
import com.example.wood121.viewdemos.bean.ModelRecyclerBean;

/**
 * Created by wood121 on 2017/12/21.
 * Android api调用情况
 */

public class APIFragment extends BaseTabContentFragment {

    public static APIFragment newInstance() {
        return new APIFragment();
    }

    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("蓝牙扫描", R.mipmap.circle_captcha, BleAcitivty.class));
        mData.add(new ModelRecyclerBean("视频播放", R.mipmap.circle_captcha, VideoActivity.class));
    }
}
