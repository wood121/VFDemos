package com.example.wood121.viewdemos.base.fragment;

/**
 * Created by wood121 on 2017/12/20.
 * Data Structure and Algorithm，
 */

public class MathJniFragment extends BaseTabFragment {

    public static MathJniFragment mathJniFragment;

    public static MathJniFragment newInstance() {
        if (mathJniFragment == null) {
            synchronized (MathJniFragment.class) {
                if (mathJniFragment == null) {
                    mathJniFragment = new MathJniFragment();
                }
            }
        }
        return mathJniFragment;
    }


    @Override
    protected void getFragDatas() {
        list.add("Math");
        list.add("JNI");
        afmAdapter.setData(list, 2);
    }
}
