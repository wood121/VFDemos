package com.example.wood121.viewdemos.apis.database_.sqlite_device;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.apis.database_.sqlite_device.bean.VirtualidDeviceBean;

import java.util.ArrayList;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2018/11/1 9:51<br>
 * 版本： v2.0<br>
 */
public class RecDeviceAdapter extends RecyclerView.Adapter<RecDeviceAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<VirtualidDeviceBean> mList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sqlite, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        VirtualidDeviceBean deviceBean = mList.get(position);
        holder.mTvIndex.setText(position + "");
        holder.mTvDeviceId.setText(deviceBean.getDeviceId());
        holder.mTvMacAddress.setText(deviceBean.getMacAddress());
        holder.mTvNetworkId.setText(deviceBean.getNetworkDeviceId());
        holder.mTvMeshId.setText(deviceBean.getMeshId() + "");
        holder.mTvPhoneId.setText(deviceBean.getPhoneId() + "");
        holder.mTvVirtualId.setText(deviceBean.getVirtualId() + "");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(ArrayList<VirtualidDeviceBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvIndex;
        private final TextView mTvDeviceId;
        private final TextView mTvMacAddress;
        private final TextView mTvNetworkId;
        private final TextView mTvMeshId;
        private final TextView mTvPhoneId;
        private final TextView mTvVirtualId;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTvIndex = itemView.findViewById(R.id.tv_index_item);
            mTvDeviceId = itemView.findViewById(R.id.tv_deviceId_item);
            mTvMacAddress = itemView.findViewById(R.id.tv_macAddress_item);
            mTvNetworkId = itemView.findViewById(R.id.tv_networkDeviceId_item);
            mTvMeshId = itemView.findViewById(R.id.tv_meshId_item);
            mTvPhoneId = itemView.findViewById(R.id.tv_phoneId_item);
            mTvVirtualId = itemView.findViewById(R.id.tv_virtualId_item);
        }
    }
}
