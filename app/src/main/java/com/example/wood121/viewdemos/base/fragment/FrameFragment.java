package com.example.wood121.viewdemos.base.fragment;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.sdk_part.GoodsTransformActivity;
import com.example.wood121.viewdemos.frame_part.LeakCanaryActivity;
import com.example.wood121.viewdemos.frame_part.Okhttp3Activity;
import com.example.wood121.viewdemos.frame_part.RetrofitActivity;
import com.example.wood121.viewdemos.frame_part.RxjavaActivity;
import com.example.wood121.viewdemos.frame_part.RxjavaRetrofitActivity;
import com.example.wood121.viewdemos.frame_part.RxjavaRetrofitTotalActivity;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        frameFragment =null;
    }
}
