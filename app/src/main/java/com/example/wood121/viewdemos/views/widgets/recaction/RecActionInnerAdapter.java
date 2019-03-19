package com.example.wood121.viewdemos.views.widgets.recaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2018/10/23 17:02<br>
 * 版本： v2.0<br>
 */
class RecActionInnerAdapter extends RecyclerView.Adapter<RecActionInnerAdapter.MyViewHolder> {

    private Context mContext;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rec_inner, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mIv_icon.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv_icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            mIv_icon = itemView.findViewById(R.id.iv_icon);
            mIv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(mContext, "iv_icon:" + getLayoutPosition());
                }
            });
        }
    }
}
