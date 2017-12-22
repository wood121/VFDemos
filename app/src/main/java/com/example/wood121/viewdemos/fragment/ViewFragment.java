package com.example.wood121.viewdemos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by wood121 on 2017/12/21.
 */

public class ViewFragment extends Fragment {

    private static final String View_FRAGMENT = "ViewFragment";
    private String msg;

    public static ViewFragment newInstance() {
        return new ViewFragment();
    }

    /**
     * 避免在横竖屏切换的时候Fragment自动调用自己的无参构造函数，导致数据丢失。
     * 在流程式的情况下适用，
     *
     * @param msg
     * @return
     */
    public static ViewFragment newInstance(String msg) {
        ViewFragment fragment = new ViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(View_FRAGMENT, msg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            msg = (String) getArguments().getSerializable(View_FRAGMENT);
        }
    }

}
