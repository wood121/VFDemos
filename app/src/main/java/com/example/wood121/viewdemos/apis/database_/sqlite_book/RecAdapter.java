package com.example.wood121.viewdemos.apis.database_.sqlite_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.apis.database_.sqlite_book.bean.Book;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sqlite_book, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Book book = mList.get(position);
        holder.mTvIndex.setText(position + "");
        holder.mTvName.setText(book.getName());
        holder.mTvAuthor.setText(book.getAuthor());
        holder.mTvPages.setText(book.getPages() + "");
        holder.mTvPrice.setText(book.getPrice() + "+");
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

        private final TextView mTvIndex;
        private final TextView mTvName;
        private final TextView mTvAuthor;
        private final TextView mTvPages;
        private final TextView mTvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTvIndex = itemView.findViewById(R.id.tv_index);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvAuthor = itemView.findViewById(R.id.tv_author);
            mTvPages = itemView.findViewById(R.id.tv_pages);
            mTvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
