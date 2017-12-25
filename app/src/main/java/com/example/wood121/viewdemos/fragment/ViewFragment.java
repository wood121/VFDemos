package com.example.wood121.viewdemos.fragment;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.activity.ArcViewActivity;
import com.example.wood121.viewdemos.activity.BingActivity;
import com.example.wood121.viewdemos.activity.CheckProcessActivity;
import com.example.wood121.viewdemos.activity.RecActivity;
import com.example.wood121.viewdemos.activity.TopbarActivity;
import com.example.wood121.viewdemos.activity.ZheActivity;
import com.example.wood121.viewdemos.bean.ModelRecyclerBean;

/**
 * Created by wood121 on 2017/12/21.
 * viewFragment，给予对象与数据
 */

public class ViewFragment extends BaseTabContentFragment {

    public static ViewFragment newInstance() {
        return new ViewFragment();
    }

    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("进度条", R.mipmap.circle_zip, ArcViewActivity.class));
        mData.add(new ModelRecyclerBean("物流进度", R.mipmap.circle_zip, CheckProcessActivity.class));
        mData.add(new ModelRecyclerBean("折线图1", R.mipmap.circle_zip, BingActivity.class));
        mData.add(new ModelRecyclerBean("折线图2", R.mipmap.circle_zip, ZheActivity.class));
        mData.add(new ModelRecyclerBean("标题栏", R.mipmap.circle_zip, TopbarActivity.class));
        mData.add(new ModelRecyclerBean("RecView", R.mipmap.circle_zip, RecActivity.class));
    }
}
