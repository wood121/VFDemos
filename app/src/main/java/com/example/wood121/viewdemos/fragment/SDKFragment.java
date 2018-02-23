package com.example.wood121.viewdemos.fragment;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.activity.BaiduMapActivity;
import com.example.wood121.viewdemos.activity.ExcelContentActivity;
import com.example.wood121.viewdemos.activity.GetAddressActivity;
import com.example.wood121.viewdemos.activity.GoodsTransformActivity;
import com.example.wood121.viewdemos.bean.ModelRecyclerBean;

/**
 * Created by wood121 on 2017/12/22.
 * 第三方SDK
 */

public class SDKFragment extends BaseTabContentFragment {

    public static SDKFragment sdkFragment;

    public static SDKFragment newInstance() {
        if (sdkFragment == null) {
            synchronized (SDKFragment.class) {
                if (sdkFragment == null) {
                    sdkFragment = new SDKFragment();
                }
            }
        }
        return sdkFragment;
    }

    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("二维码生成", R.mipmap.circle_captcha, GoodsTransformActivity.class));
        mData.add(new ModelRecyclerBean("Excel比对", R.mipmap.circle_captcha, ExcelContentActivity.class));
        mData.add(new ModelRecyclerBean("BaiduMap", R.mipmap.circle_captcha, BaiduMapActivity.class));
        mData.add(new ModelRecyclerBean("selectYourAddress", R.mipmap.circle_captcha, GetAddressActivity.class));
    }
}
