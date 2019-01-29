package com.example.wood121.viewdemos.base.fragment;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.view_part.ArcViewActivity;
import com.example.wood121.viewdemos.view_part.BingActivity;
import com.example.wood121.viewdemos.view_part.CheckProcessActivity;
import com.example.wood121.viewdemos.view_part.ContextActivity;
import com.example.wood121.viewdemos.view_part.RecyclerView.RecActivity;
import com.example.wood121.viewdemos.view_part.TopbarActivity;
import com.example.wood121.viewdemos.view_part.ViewActionsActivity;
import com.example.wood121.viewdemos.view_part.ZheActivity;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;

/**
 * Created by wood121 on 2017/12/21.
 * apiFragment，给予对象与数据
 */

public class ViewFragment extends BaseTabContentFragment {
    public static ViewFragment apiFragment;

    public static ViewFragment newInstance() {
        if (apiFragment == null) {
            synchronized (ViewFragment.class) {
                if (apiFragment == null) {
                    apiFragment = new ViewFragment();
                }
            }
        }
        return apiFragment;
    }

    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("进度条", R.mipmap.circle_zip, ArcViewActivity.class));
        mData.add(new ModelRecyclerBean("物流进度", R.mipmap.circle_zip, CheckProcessActivity.class));
        mData.add(new ModelRecyclerBean("折线图1", R.mipmap.circle_zip, BingActivity.class));
        mData.add(new ModelRecyclerBean("折线图2", R.mipmap.circle_zip, ZheActivity.class));
        mData.add(new ModelRecyclerBean("标题栏", R.mipmap.circle_zip, TopbarActivity.class));
        mData.add(new ModelRecyclerBean("RecView", R.mipmap.circle_zip, RecActivity.class));
        mData.add(new ModelRecyclerBean("ViewActions", R.mipmap.circle_zip, ViewActionsActivity.class));
        mData.add(new ModelRecyclerBean("AppContext_dialog", R.mipmap.circle_captcha, ContextActivity.class));
    }
}
