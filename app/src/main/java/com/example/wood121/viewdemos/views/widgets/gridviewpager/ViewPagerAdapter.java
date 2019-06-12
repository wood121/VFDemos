package com.example.wood121.viewdemos.views.widgets.gridviewpager;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by lijuan on 2016/9/12.
 */
public class ViewPagerAdapter<T extends View> extends PagerAdapter {
    private List<T> mViewList;

    public ViewPagerAdapter(List<T> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return (mViewList.get(position));
    }

    @Override
    public int getCount() {
        return mViewList == null ? 0 : mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}