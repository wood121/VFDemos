package com.example.wood121.viewdemos.views.widgets.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseFragment;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/3/21 15:37<br>
 * 版本： v2.0<br>
 */
public class TabFragment extends BaseFragment {
    private Context mTabContext;
    private String mTabName;
    private TextView mTvContent;

    public static TabFragment getInstance() {
        return TabFragmentHolder.sTabFragment;
    }

    public void setData(Context context, String tabName) {
        this.mTabContext = context;
        this.mTabName = tabName;
    }

    static class TabFragmentHolder {
        private static TabFragment sTabFragment = new TabFragment();
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setListener() {
        mTvContent = findViewById(R.id.tv_content);

        mTvContent.setText(mTabName + "...");
    }
}
