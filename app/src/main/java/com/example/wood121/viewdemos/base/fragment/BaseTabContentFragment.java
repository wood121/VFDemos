package com.example.wood121.viewdemos.base.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wood121.viewdemos.base.BaseFragment;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.adapter.TabContentRecAdapter;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;
import com.example.wood121.viewdemos.util.DisplayUtils;
import com.example.wood121.viewdemos.util.RecyclerViewDivider;
import com.example.wood121.viewdemos.util.VFSkipActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wood121 on 2017/12/22.
 * 子fragment(APIFragment,ViewFragment,framefragment,sdkfragment,... 的父类)
 */

public class BaseTabContentFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private int mColumnCount = 3;
    protected TabContentRecAdapter tabContentRecAdapter;
    protected List<ModelRecyclerBean> mData;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_base_avfsm;
    }


    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {
        //列表数据
        if (mData == null)
            mData = new ArrayList<>();
        mData.clear();
        getFragDatas(); //获取不同fragment的数据

        //布局格式
        if (mData.size() <= 3) {
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        } else {
            recyclerview.setLayoutManager(new GridLayoutManager(mContext, mColumnCount));
        }

        //分割线
        recyclerview.addItemDecoration(new RecyclerViewDivider(DisplayUtils.dp2px(mContext, 5f)));

        //适配器
        if (tabContentRecAdapter == null) {
            tabContentRecAdapter = new TabContentRecAdapter();
        }
        tabContentRecAdapter.setOnItemClickListener(new TabContentRecAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, ModelRecyclerBean modelRecyclerBean) {
                VFSkipActivity.skipActivity(mActivity, modelRecyclerBean.getActivity());
            }
        });
        recyclerview.setHasFixedSize(true);
        recyclerview.setAdapter(tabContentRecAdapter);
        tabContentRecAdapter.setData(mData);  //刷新页面
    }

    /**
     * 添加ModelRecyclerBean对象
     */
    protected void getFragDatas() {

    }
}
