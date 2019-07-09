package com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wood121.viewdemos.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @date: 2019/6/28
 * @version:
 * @author: liuzhengling
 * @des: 一个textView一行，然后还可以点击的适配器
 */
public class RecItemTVAdapter extends RecyclerView.Adapter<RecItemTVAdapter.MyViewHolder> {

    private ArrayList<String> mList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec_okhttp, viewGroup, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.btn_item.setText(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setData(ArrayList<String> list) {
        this.mList = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final Button btn_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_item = itemView.findViewById(R.id.btn_item);
            btn_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, getLayoutPosition());
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(View v, int postion);
    }

    public onItemClickListener listener;

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
