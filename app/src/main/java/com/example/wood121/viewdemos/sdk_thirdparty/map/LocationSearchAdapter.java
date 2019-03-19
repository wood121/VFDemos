package com.example.wood121.viewdemos.sdk_thirdparty.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.example.wood121.viewdemos.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by wood121 on 2018/2/23.
 */

public class LocationSearchAdapter extends RecyclerView.Adapter<LocationSearchAdapter.MyViewHolder> {

    private final Context mContext;
    private List<PoiInfo> mList;
    private onItemClickListener listener;

    public LocationSearchAdapter(Context context) {
        this.mContext = context;
    }

    public void updateRecView(List<PoiInfo> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.recycle_location_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PoiInfo poiInfo = mList.get(position);

        holder.tv_name.setText("" + poiInfo.name);
        holder.tv_adds.setText("" + poiInfo.address);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_adds;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_adds = (TextView) itemView.findViewById(R.id.tv_adds);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, mList.get(getLayoutPosition()).address);
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(View view, String address);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

}
