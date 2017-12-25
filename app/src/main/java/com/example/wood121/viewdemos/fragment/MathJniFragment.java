package com.example.wood121.viewdemos.fragment;

/**
 * Created by wood121 on 2017/12/20.
 * Data Structure and Algorithm，
 */

public class MathJniFragment extends BaseTabFragment {


    public static MathJniFragment newInstance() {
        return new MathJniFragment();
    }

    @Override
    protected void getFragDatas() {
        list.add("Math");
        list.add("JNI");
        afmAdapter.setData(list, 2);
    }
}
