package com.example.wood121.viewdemos.fragment;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.activity.GoodsTransformActivity;
import com.example.wood121.viewdemos.bean.ModelRecyclerBean;

/**
 * Created by wood121 on 2017/12/22.
 * 第三方框架的引用
 */

public class FrameFragment extends BaseTabContentFragment {

    public static FrameFragment newInstance() {
        return new FrameFragment();
    }

    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("二维码生成", R.mipmap.circle_bullet, GoodsTransformActivity.class));
        mData.add(new ModelRecyclerBean("二维码生成", R.mipmap.circle_bullet, GoodsTransformActivity.class));
        mData.add(new ModelRecyclerBean("二维码生成", R.mipmap.circle_bullet, GoodsTransformActivity.class));
    }
}
