package com.example.wood121.viewdemos.views.widgets.viewpager;

import android.os.Bundle;
import android.util.Log;

import com.example.gridviewpager.GridItemClickListener;
import com.example.gridviewpager.GridItemLongClickListener;
import com.example.gridviewpager.GridViewPager;
import com.example.gridviewpager.Model;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Banner轮播图、
 * <p>
 * Vp频道分类:viewPager,gridview
 */
public class ViewPagerActivity extends BaseActivity {
    private String[] titles = {"美食", "电影", "酒店住宿", "休闲娱乐", "外卖", "自助餐", "KTV", "机票/火车票", "周边游", "美甲美睫",
            "火锅", "生日蛋糕", "甜品饮品", "水上乐园", "汽车服务", "美发", "丽人", "景点", "足疗按摩", "运动健身", "健身", "超市", "买菜",
            "今日新单", "小吃快餐", "面膜", "洗浴/汗蒸", "母婴亲子", "生活服务", "婚纱摄影", "学习培训", "家装", "结婚", "全部分配"};
    private List<Model> mMData;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mMData = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getPackageName());
            mMData.add(new Model(titles[i], imageId));
        }
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void initPageViewListener() {
        GridViewPager mGridViewPager = findViewById(R.id.mGridViewPager);
        //初始化数据源
        mGridViewPager
                //设置每一页的容量
                .setPageSize(10)
                .setGridItemClickListener(new GridItemClickListener() {
                    @Override
                    public void click(int pos, int position, String str) {
                        Log.d("123", pos + "/" + str);
                    }
                })
                .setGridItemLongClickListener(new GridItemLongClickListener() {
                    @Override
                    public void click(int pos, int position, String str) {
                        Log.d("456", pos + "/" + str);
                    }
                }).init(mMData);

    }
}
