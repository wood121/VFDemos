package com.example.wood121.viewdemos.fragment;

import android.os.Bundle;

import com.example.wood121.viewdemos.BaseFragment;

/**
 * Created by wood121 on 2017/12/20.
 * 抽取出的主业务流程：e蜂社用户版、商户版、物业版
 */

public class MineFragment extends BaseFragment {
    private static String Mine_FRAGMENT = "MineFragment";
    private String msg;

    public MineFragment() {
    }

    public static MineFragment newInstance() {
        return new MineFragment();
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
        bundle.putSerializable(Mine_FRAGMENT, msg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            msg = (String) getArguments().getSerializable(Mine_FRAGMENT);
        }
    }

    @Override
    protected int setLayoutResouceId() {
        return 0;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {

    }
}
