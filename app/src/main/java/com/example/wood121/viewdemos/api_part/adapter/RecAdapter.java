package com.example.wood121.viewdemos.api_part.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.api_part.database.bean.Book;

import java.util.ArrayList;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2018/11/1 9:51<br>
 * 版本： v2.0<br>
 */
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Book> mList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sqlite, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Book book = mList.get(position);
        holder.mTvIndex.setText(position + "");
        holder.mTvName.setText(book.getName());
        holder.mTvAuthor.setText(book.getAuthor());
        holder.mTvPages.setText(book.getPages() + "");
        holder.mTvPrices.setText(book.getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(ArrayList<Book> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTvName;
        private final TextView mTvAuthor;
        private final TextView mTvPages;
        private final TextView mTvPrices;
        private final TextView mTvIndex;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTvName = itemView.findViewById(R.id.tv_name);
            mTvAuthor = itemView.findViewById(R.id.tv_author);
            mTvPages = itemView.findViewById(R.id.tv_pages);
            mTvPrices = itemView.findViewById(R.id.tv_prices);
            mTvIndex = itemView.findViewById(R.id.tv_index);
        }
    }
}
