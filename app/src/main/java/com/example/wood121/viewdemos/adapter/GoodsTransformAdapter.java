package com.example.wood121.viewdemos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.wood121.viewdemos.R;

import java.util.List;
import java.util.Map;

/**
 * Created by wood121 on 2017/8/4.
 */

public class GoodsTransformAdapter extends BaseAdapter {

    private final Context context;
    private final List<Map<String,Object>> mList;
    private LayoutInflater layoutInflater;

    public GoodsTransformAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.item_transformview,null);

        return null;
    }
}
