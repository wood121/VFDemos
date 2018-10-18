package com.example.wood121.viewdemos.base.fragment;

import android.os.Bundle;
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

import butterknife.BindView;

/**
 * Created by wood121 on 2017/12/20.
 * 抽取出的主业务流程：e蜂社用户版、商户版、物业版
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<ModelRecyclerBean> arrayList;

    public MineFragment() {
    }

    public static MineFragment mineFragment;

    public static MineFragment newInstance() {
        if (mineFragment == null) {
            synchronized (MineFragment.class) {
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
            }
        }
        return mineFragment;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData(Bundle arguments) {
        arrayList = new ArrayList<>();
//        arrayList.add(new ModelRecyclerBean("go设备管理", R.mipmap.circle_credit_card, DeviceManagementActivity.class));
//        arrayList.add(new ModelRecyclerBean("go商品购物", R.mipmap.circle_credit_card, DeviceSellActivity.class));
    }

    @Override
    protected void setListener() {
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.addItemDecoration(new RecyclerViewDivider(DisplayUtils.dp2px(mContext, 5f)));
        TabContentRecAdapter tabContentRecAdapter = new TabContentRecAdapter();
        tabContentRecAdapter.setData(arrayList);
        tabContentRecAdapter.setOnItemClickListener(new TabContentRecAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, ModelRecyclerBean modelRecyclerBean) {
                VFSkipActivity.skipActivity(mContext, modelRecyclerBean.getActivity());
            }
        });
        recyclerview.setAdapter(tabContentRecAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mineFragment = null;
    }
}
