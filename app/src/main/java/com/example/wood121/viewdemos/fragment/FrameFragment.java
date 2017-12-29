package com.example.wood121.viewdemos.fragment;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.activity.GoodsTransformActivity;
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
        mData.add(new ModelRecyclerBean("未知内容", R.mipmap.circle_bullet, GoodsTransformActivity.class));
    }
}
