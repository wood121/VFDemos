package com.example.wood121.viewdemos.base.fragment;

/**
 * Created by wood121 on 2017/12/20.
 */

public class FrameSDKFragment extends BaseTabFragment {

    public static FrameSDKFragment frameSDKFragment;

    public static FrameSDKFragment newInstance() {
        if (frameSDKFragment == null) {
            synchronized (FrameSDKFragment.class) {
                if (frameSDKFragment == null) {
                    frameSDKFragment = new FrameSDKFragment();
                }
            }
        }
        return frameSDKFragment;
    }

    @Override
    protected void getFragDatas() {
        list.add("Frame");
        list.add("SDK");
        afmAdapter.setData(list, 1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        frameSDKFragment = null;
    }
}
