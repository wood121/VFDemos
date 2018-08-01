package com.example.wood121.viewdemos.fragment;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.activity.GoodsTransformActivity;
import com.example.wood121.viewdemos.activity.LeakCanaryActivity;
import com.example.wood121.viewdemos.activity.Okhttp3Activity;
import com.example.wood121.viewdemos.activity.RetrofitActivity;
import com.example.wood121.viewdemos.activity.RxjavaActivity;
import com.example.wood121.viewdemos.activity.RxjavaRetrofitActivity;
import com.example.wood121.viewdemos.activity.RxjavaRetrofitTotalActivity;
import com.example.wood121.viewdemos.bean.ModelRecyclerBean;

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
        mData.add(new ModelRecyclerBean("未知内容", R.mipmap.circle_captcha, GoodsTransformActivity.class));
        mData.add(new ModelRecyclerBean("Retrofit", R.mipmap.circle_captcha, RetrofitActivity.class));
        mData.add(new ModelRecyclerBean("Rxjava", R.mipmap.circle_captcha, RxjavaActivity.class));
        mData.add(new ModelRecyclerBean("Rxjava+Retrofit", R.mipmap.circle_captcha, RxjavaRetrofitActivity.class));
        mData.add(new ModelRecyclerBean("Rxjava+Retrofit封装", R.mipmap.circle_captcha, RxjavaRetrofitTotalActivity.class));
        mData.add(new ModelRecyclerBean("LeakCanary", R.mipmap.circle_captcha, LeakCanaryActivity.class));
        mData.add(new ModelRecyclerBean("Okhttp3", R.mipmap.circle_captcha, Okhttp3Activity.class));
    }
}
