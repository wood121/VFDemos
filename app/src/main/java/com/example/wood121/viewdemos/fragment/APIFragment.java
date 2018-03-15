package com.example.wood121.viewdemos.fragment;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.activity.BleAcitivty;
import com.example.wood121.viewdemos.activity.DataStorageActivity;
import com.example.wood121.viewdemos.activity.IjkPlayerActvitiy;
import com.example.wood121.viewdemos.activity.MyLoaderActivity;
import com.example.wood121.viewdemos.activity.ServiceActivity;
import com.example.wood121.viewdemos.activity.VideoActivity;
import com.example.wood121.viewdemos.bean.ModelRecyclerBean;

/**
 * Created by wood121 on 2017/12/21.
 * Android api调用情况
 */

public class APIFragment extends BaseTabContentFragment {

    public static APIFragment apiFragment;

    public static APIFragment newInstance() {
        if (apiFragment == null) {
            synchronized (APIFragment.class) {
                if (apiFragment == null) {
                    apiFragment = new APIFragment();
                }
            }
        }
        return apiFragment;
    }

    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("蓝牙扫描", R.mipmap.circle_captcha, BleAcitivty.class));
        mData.add(new ModelRecyclerBean("MediaPlayer", R.mipmap.circle_captcha, VideoActivity.class));
        mData.add(new ModelRecyclerBean("IjkPlayer", R.mipmap.circle_captcha, IjkPlayerActvitiy.class));
        mData.add(new ModelRecyclerBean("Service", R.mipmap.circle_captcha, ServiceActivity.class));
        mData.add(new ModelRecyclerBean("Loader加载图片文件夹", R.mipmap.circle_captcha, MyLoaderActivity.class));
        mData.add(new ModelRecyclerBean("数据存储", R.mipmap.circle_captcha, DataStorageActivity.class));
    }

}
