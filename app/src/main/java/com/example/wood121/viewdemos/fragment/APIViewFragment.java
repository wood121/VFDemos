package com.example.wood121.viewdemos.fragment;

/**
 * Created by wood121 on 2017/12/20.
 */

public class APIViewFragment extends BaseTabFragment {

    public static APIViewFragment newInstance() {
        return new APIViewFragment();
    }

    /**
     * 子标题和类型
     */
    @Override
    protected void getFragDatas() {
        list.add("API");
        list.add("View");
        afmAdapter.setData(list, 0);
    }
}

