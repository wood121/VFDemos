package com.example.wood121.viewdemos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;

import java.util.ArrayList;

/**
 * Created by wood121 on 2017/10/11.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private final Context mContext;
    private ArrayList<String> datas;

    public MyAdapter(Context context, ArrayList<String> datas) {
        this.mContext = context;
        this.datas = datas;

        Log.e("data", "MyAdapter");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.recycle_item, parent, false);
//        LayoutInflater.from(mContext).inflate();
        Log.e("data", "onCreateViewHolder");
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String contents = datas.get(position);
        holder.tvName.setText(contents);
        Log.e("data", "onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        Log.e("data", "getItemCount" + datas.size());
//        return datas.size();
        return 100;
    }

    public void addData(int i, String str) {
        datas.add(i, str);
        notifyItemInserted(i);
    }

    public void remove(int i) {
        datas.remove(i);
        notifyItemRemoved(i);
//        notifyDataSetChanged();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgView;
        private TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            Log.e("data", "MyViewHolder");
            imgView = (ImageView) itemView.findViewById(R.id.iv_img);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onItemClick(view, datas.get(getLayoutPosition()));
                }
            });

        }
    }

    public interface onItemClickListener {
        void onItemClick(View v, String str);
    }

    public onItemClickListener listener;

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;

    }

}
