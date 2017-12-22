package com.example.wood121.viewdemos.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wood121.viewdemos.BaseFragment;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.activity.ArcViewActivity;
import com.example.wood121.viewdemos.activity.BingActivity;
import com.example.wood121.viewdemos.activity.BleAcitivty;
import com.example.wood121.viewdemos.activity.CheckProcessActivity;
import com.example.wood121.viewdemos.activity.ContactActivity;
import com.example.wood121.viewdemos.activity.GoodsTransformActivity;
import com.example.wood121.viewdemos.activity.RecActivity;
import com.example.wood121.viewdemos.activity.TopbarActivity;
import com.example.wood121.viewdemos.activity.VideoActivity;
import com.example.wood121.viewdemos.activity.ZheActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wood121 on 2017/12/21.
 */

public class APIFragment extends BaseFragment implements View.OnClickListener {

    private static final String API_FRAGMENT = "APIFragment";
    @BindView(R.id.bt_goodsTransformProcess)
    TextView btGoodsTransformProcess;
    @BindView(R.id.bt_checkProcesss)
    TextView btCheckProcesss;
    @BindView(R.id.bt_bing)
    TextView btBing;
    @BindView(R.id.bt_zhe)
    TextView btZhe;
    @BindView(R.id.bt_topbar)
    TextView btTopbar;
    @BindView(R.id.bt_recycleView)
    TextView btRecycleView;
    @BindView(R.id.bt_viewDemo3)
    TextView btViewDemo3;
    @BindView(R.id.bt_mediaplayer)
    TextView btMediaplayer;
    @BindView(R.id.bt_ijkplayer)
    TextView btIjkplayer;
    @BindView(R.id.bt_qx)
    TextView btQx;
    @BindView(R.id.contact_demo)
    TextView contactDemo;
    
    Unbinder unbinder;
    private String msg;

    public static APIFragment newInstance() {
        return new APIFragment();
    }

    /**
     * 避免在横竖屏切换的时候Fragment自动调用自己的无参构造函数，导致数据丢失。
     * 在流程式的情况下适用，
     *
     * @param msg
     * @return
     */
    public static APIFragment newInstance(String msg) {
        APIFragment fragment = new APIFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(API_FRAGMENT, msg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            msg = (String) getArguments().getSerializable(API_FRAGMENT);
        }
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_api;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_goodsTransformProcess, R.id.bt_checkProcesss, R.id.bt_bing, R.id.bt_zhe, R.id.bt_topbar, R.id.bt_recycleView, R.id.bt_viewDemo3, R.id.bt_mediaplayer, R.id.bt_ijkplayer, R.id.bt_qx, R.id.contact_demo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_goodsTransformProcess:
                startActivity(new Intent(mActivity, GoodsTransformActivity.class));
                Toast.makeText(mActivity, "hha", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_checkProcesss:
                startActivity(new Intent(mActivity, CheckProcessActivity.class));
                break;
            case R.id.bt_bing:
                startActivity(new Intent(mActivity, BingActivity.class));
                break;
            case R.id.bt_zhe:
                startActivity(new Intent(mActivity, ZheActivity.class));
                break;
            case R.id.bt_topbar:
                startActivity(new Intent(mActivity, TopbarActivity.class));
                break;
            case R.id.bt_recycleView:
                startActivity(new Intent(mActivity, RecActivity.class));
                break;
            case R.id.bt_viewDemo3:
                startActivity(new Intent(mActivity, ArcViewActivity.class));
                break;
            case R.id.bt_mediaplayer:
                startActivity(new Intent(mActivity, VideoActivity.class));
                break;
            case R.id.bt_ijkplayer:
                startActivity(new Intent(mActivity, VideoActivity.class));
                break;
            case R.id.bt_qx:
                startActivity(new Intent(mActivity, BleAcitivty.class));
                break;
            case R.id.contact_demo:
                startActivity(new Intent(mActivity, ContactActivity.class));
                break;
        }
    }
}
