package com.example.wood121.viewdemos.views.widgets.tablayout;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;
import com.example.wood121.viewdemos.views.widgets.RecyclerView.RecItemAdapter;
import com.example.wood121.viewdemos.views.widgets.tablayout.entity.DeviceBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/3/22 17:53<br>
 * 版本： v2.0<br>
 */
public class TabRecAdapter extends RecyclerView.Adapter<TabRecAdapter.MyViewHolder> {
    private final List<DeviceBean> mDeviceList;
    private Context mContext;

    public TabRecAdapter(List<DeviceBean> deviceList) {
        this.mDeviceList = deviceList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_tabrec, parent, false);
        Log.e("data", "onCreateViewHolder");
        return new TabRecAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DeviceBean deviceBean = mDeviceList.get(position);
        holder.tvName.setText(deviceBean.getRoomName());
        holder.tv_model.setText("取值范围:" + deviceBean.getKeyRanage());
    }

    @Override
    public int getItemCount() {
        return mDeviceList == null ? 0 : mDeviceList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tv_model;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tv_model = itemView.findViewById(R.id.tv_model);
            itemView.setOnClickListener(view -> {
                //hahha
                DeviceBean deviceBean = mDeviceList.get(getLayoutPosition());
                if (listener != null) {
                    listener.onItemClick(view, deviceBean);
                }
            });

            tv_model.setOnClickListener(v -> {
                DeviceBean deviceBean = mDeviceList.get(getLayoutPosition());
                ToastUtil.showToast(mContext, "数值是:" + deviceBean.getKeyRanage());
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(View v, DeviceBean deviceBean);
    }

    public TabRecAdapter.onItemClickListener listener;

    public void setOnItemClickListener(TabRecAdapter.onItemClickListener listener) {
        this.listener = listener;
    }
}
