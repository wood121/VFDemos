package com.example.wood121.viewdemos.base.fragment;

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

    }
}
