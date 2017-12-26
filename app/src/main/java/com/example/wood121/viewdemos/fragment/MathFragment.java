package com.example.wood121.viewdemos.fragment;

import static com.example.wood121.viewdemos.fragment.JNIFragment.jniFragment;

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

    }
}
