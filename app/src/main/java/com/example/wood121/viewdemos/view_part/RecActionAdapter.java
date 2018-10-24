package com.example.wood121.viewdemos.view_part;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;
import com.example.wood121.viewdemos.view_part.RecyclerView.RecItemAdapter;

import java.util.ArrayList;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2018/10/23 16:37<br>
 * 版本： v2.0<br>
 */
class RecActionAdapter extends RecyclerView.Adapter<RecActionAdapter.MyViewHolder> {

    private final Context mContext;
    private final ArrayList<String> mList;

    public RecActionAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_outer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String positionStr = mList.get(position);
        holder.mTvPosition.setText(positionStr);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvPosition;
        private final ItemRecyclerView mInnerRecycler;

        @SuppressLint("ClickableViewAccessibility")
        public MyViewHolder(View itemView) {
            super(itemView);

            mTvPosition = itemView.findViewById(R.id.tv_position);
            mInnerRecycler = itemView.findViewById(R.id.innter_rec);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(mContext, mTvPosition.getText().toString());
                    Log.e("wood", "outer:::setOnClickListener");
                }
            });

            LinearLayoutManager horiManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            mInnerRecycler.setLayoutManager(horiManager);
            RecActionInnerAdapter recActionInnerAdapter = new RecActionInnerAdapter();
            mInnerRecycler.setAdapter(recActionInnerAdapter);

//            mInnerRecycler.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if (event.getAction() == MotionEvent.ACTION_UP) {
//                        mInnerRecycler.performClick();
//                    }
//                    return false;
//                }
//            });


            mInnerRecycler.setFocusable(true);
            mInnerRecycler.setEnabled(true);
            mInnerRecycler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(mContext, mTvPosition.getText().toString());
                    Log.e("wood", "mInnerRecycler.setOnClickListener");
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(View v, String str);
    }

    public RecItemAdapter.onItemClickListener listener;

    public void setOnItemClickListener(RecItemAdapter.onItemClickListener listener) {
        this.listener = listener;
    }
}
