package com.example.wood121.viewdemos.views;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.fragments.BaseTabContentFragment;
import com.example.wood121.viewdemos.views.charts.ChartsActivity;
import com.example.wood121.viewdemos.views.webview.WebViewActivity;
import com.example.wood121.viewdemos.views.widgets.WidgetsActivity;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;
import com.example.wood121.viewdemos.views.widgets_self.WidgetsSelfActivity;

/**
 * Created by wood121 on 2017/12/21.
 * apiFragment，给予对象与数据
 */

public class ViewFragment extends BaseTabContentFragment {
    public static ViewFragment apiFragment;

    public static ViewFragment newInstance() {
        if (apiFragment == null) {
            synchronized (ViewFragment.class) {
                if (apiFragment == null) {
                    apiFragment = new ViewFragment();
                }
            }
        }
        return apiFragment;
    }

    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("基础控件", R.mipmap.circle_zip, WidgetsActivity.class));
        mData.add(new ModelRecyclerBean("图表控件", R.mipmap.circle_zip, ChartsActivity.class));
        mData.add(new ModelRecyclerBean("自定义控件", R.mipmap.circle_zip, WidgetsSelfActivity.class));
        mData.add(new ModelRecyclerBean("WebView", R.mipmap.circle_zip, WebViewActivity.class));
    }
}
