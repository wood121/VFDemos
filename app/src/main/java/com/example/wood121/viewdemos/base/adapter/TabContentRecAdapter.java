package com.example.wood121.viewdemos.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wood121 on 2017/10/12.
 */

public class TabContentRecAdapter extends RecyclerView.Adapter<TabContentRecAdapter.MyViewHolder> {

    private List<ModelRecyclerBean> mDatas;
    private Context context;

    /**
     * 这个对象是唯一的，创建的集合就是唯一的,this.mDatas = mData会受外界数据影响
     */
    public TabContentRecAdapter() {
        if (mDatas == null)
            mDatas = new ArrayList<>();
    }

    public void setData(List<ModelRecyclerBean> mData) {
        mDatas.clear();
        mDatas.addAll(mData);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        context = view.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelRecyclerBean modelRecyclerBean = mDatas.get(position);
        holder.iv.setImageResource(modelRecyclerBean.getImage());

//        Glide.with(context).
//                load(holder.mItem.getImage()).
//                diskCacheStrategy(DiskCacheStrategy.RESULT).
//                thumbnail(0.5f).
//                priority(Priority.HIGH).
//                placeholder(R.drawable.pikachu_sit).
//                error(R.drawable.pikachu_sit).
//                fallback(R.drawable.pikachu_sit).
//                into(holder.imageView);
        holder.tvName.setText(modelRecyclerBean.getName());
        Log.e("wood121", "onBindViewHolder:" + modelRecyclerBean.getName());
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onItemClick(view, mDatas.get(getLayoutPosition()));
                    } else {
                        Log.e("wood121", "recyclerview未设置监听事件");
                    }
                }
            });
            iv = (ImageView) itemView.findViewById(R.id.imageView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(View view, ModelRecyclerBean modelRecyclerBean);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mListener = listener;
    }
}