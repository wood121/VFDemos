package com.example.wood121.viewdemos.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.wood121.viewdemos.BaseFragment;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.adapter.APIViewAdapter;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by wood121 on 2017/12/20.
 */

public class APIViewFragment extends BaseFragment {
    private static String APIview_FRAGMENT = "APIViewFragment";

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    Unbinder unbinder;
    private String msg;

    public static APIViewFragment newInstance() {
        return new APIViewFragment();
    }

    /**
     * 避免在横竖屏切换的时候Fragment自动调用自己的无参构造函数，导致数据丢失。
     * 在流程式的情况下适用，
     *
     * @param msg
     * @return
     */
    public static APIViewFragment newInstance(String msg) {
        APIViewFragment fragment = new APIViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(APIview_FRAGMENT, msg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            msg = (String) getArguments().getSerializable(APIview_FRAGMENT);
        }
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_apiview;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {
        //viewpager的设置
        viewPager.setOffscreenPageLimit(2);
        APIViewAdapter apiViewAdapter = new APIViewAdapter(mActivity.getSupportFragmentManager());
        viewPager.setAdapter(apiViewAdapter);
        //TabLayout绑定ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

