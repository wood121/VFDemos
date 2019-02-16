package com.example.wood121.viewdemos.frames;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.fragments.BaseTabContentFragment;
import com.example.wood121.viewdemos.frames.net.multicast.ChatActivity;
import com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.HttpRequestActivity;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;
import com.example.wood121.viewdemos.frames.wireless_iot.WirelessIotActivity;

/**
 * Created by wood121 on 2017/12/22.
 * 第三方框架的引用
 */

public class FrameFragment extends BaseTabContentFragment {

    public static FrameFragment frameFragment;

    public static FrameFragment newInstance() {
        if (frameFragment == null) {
            synchronized (FrameFragment.class) {
                if (frameFragment == null) {
                    frameFragment = new FrameFragment();
                }
            }
        }
        return frameFragment;
    }

    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("LeakCanary", R.mipmap.circle_captcha, LeakCanaryActivity.class));
        mData.add(new ModelRecyclerBean("multicast", R.mipmap.circle_captcha, ChatActivity.class));
        mData.add(new ModelRecyclerBean("Http请求", R.mipmap.circle_captcha, HttpRequestActivity.class));
        mData.add(new ModelRecyclerBean("无线网络-IoT", R.mipmap.circle_captcha, WirelessIotActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        frameFragment = null;
    }
}
