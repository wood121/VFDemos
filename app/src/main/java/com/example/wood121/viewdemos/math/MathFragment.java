package com.example.wood121.viewdemos.math;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;
import com.example.wood121.viewdemos.base.fragments.BaseTabContentFragment;


/**
 * Created by wood121 on 2017/12/22.
 */

public class MathFragment extends BaseTabContentFragment {

    public static MathFragment mathFragment;

    public static MathFragment newInstance() {
        if (mathFragment == null) {
            synchronized (MathFragment.class) {
                if (mathFragment == null) {
                    mathFragment = new MathFragment();
                }
            }
        }
        return mathFragment;
    }


    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("动态代理", R.mipmap.circle_captcha, ProxyActivity.class));
    }
}
