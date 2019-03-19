package com.example.wood121.viewdemos.views.widgets.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by wood121 on 2017/10/11.
 * 1.创建viewHolder,完成 class RecItemAdapter extends RecyclerView.Adapter<RecItemAdapter.MyViewHolder>
 * 2.构造方法中&备用方法传入数据
 * 3.onCreateViewHolder创建view，设置view或者item的点击事件
 * 4.onBindViewHolder进行view与数据的绑定
 */

public class RecItemAdapter extends RecyclerView.Adapter<RecItemAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<String> datas;

    public RecItemAdapter(Context context, ArrayList<String> datas) {
        this.mContext = context;
        this.datas = datas;

        Log.e("data", "RecItemAdapter");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.recycle_item, parent, false);
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
        private final TextView tvDel;

        public MyViewHolder(View itemView) {
            super(itemView);
            Log.e("data", "MyViewHolder");

            tvDel = (TextView) itemView.findViewById(R.id.tv_del);
            imgView = (ImageView) itemView.findViewById(R.id.iv_img);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onItemClick(view, datas.get(getLayoutPosition()));
                }
            });

            tvDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(mContext, datas.get(getLayoutPosition()));
                    //局部删除与刷新
                    datas.remove(getLayoutPosition());
                    notifyItemRemoved(getLayoutPosition());
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
