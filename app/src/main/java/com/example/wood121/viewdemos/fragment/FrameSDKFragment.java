package com.example.wood121.viewdemos.fragment;

/**
 * Created by wood121 on 2017/12/20.
 */

public class FrameSDKFragment extends BaseTabFragment {

    public static FrameSDKFragment newInstance() {
        return new FrameSDKFragment();
    }

    @Override
    protected void getFragDatas() {
        list.add("Frame");
        list.add("SDK");
        afmAdapter.setData(list, 1);
    }
}
