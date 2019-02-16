package com.example.wood121.viewdemos.sdk_thirdparty;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.fragments.BaseTabContentFragment;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;
import com.example.wood121.viewdemos.sdk_thirdparty.map.MapActivity;

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
        mData.add(new ModelRecyclerBean("登录", R.mipmap.circle_captcha, LoginActivity.class));
        mData.add(new ModelRecyclerBean("分享", R.mipmap.circle_captcha, LoginActivity.class));
        mData.add(new ModelRecyclerBean("地图", R.mipmap.circle_captcha, MapActivity.class));
        mData.add(new ModelRecyclerBean("二维码", R.mipmap.circle_captcha, QrCodeActivity.class));
        mData.add(new ModelRecyclerBean("支付", R.mipmap.circle_captcha, LoginActivity.class));
        mData.add(new ModelRecyclerBean("讯飞语音", R.mipmap.circle_captcha, LoginActivity.class));
        mData.add(new ModelRecyclerBean("OCR", R.mipmap.circle_captcha, LoginActivity.class));
        mData.add(new ModelRecyclerBean("极光推送", R.mipmap.circle_captcha, LoginActivity.class));
        mData.add(new ModelRecyclerBean("Bugly", R.mipmap.circle_captcha, LoginActivity.class));
        mData.add(new ModelRecyclerBean("友盟统计", R.mipmap.circle_captcha, LoginActivity.class));
        mData.add(new ModelRecyclerBean("Excel比对", R.mipmap.circle_captcha, ExcelContentActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sdkFragment = null;
    }
}
