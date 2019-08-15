package com.example.wood121.viewdemos.jnis;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;
import com.example.wood121.viewdemos.base.fragments.BaseTabContentFragment;
import com.example.wood121.viewdemos.jnis.canmera.CanmeraActivity;

/**
 * Created by wood121 on 2017/12/22.
 */

public class JNIFragment extends BaseTabContentFragment {

    public static JNIFragment jniFragment;

    public static JNIFragment newInstance() {
        if (jniFragment == null) {
            synchronized (JNIFragment.class) {
                if (jniFragment == null) {
                    jniFragment = new JNIFragment();
                }
            }
        }
        return jniFragment;
    }

    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("JNIdemo", R.mipmap.circle_captcha, JNIDemoActivity.class));
        mData.add(new ModelRecyclerBean("视力检测", R.mipmap.circle_captcha, CanmeraActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        jniFragment = null;
    }
}
