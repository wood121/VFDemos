package com.example.wood121.viewdemos.views.widgets.RecyclerView.recitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wood121.viewdemos.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @date: 2019/7/27
 * @version:
 * @author: liuzhengling
 * @des:
 */
public class ItemGroupChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int GROUP_ITEM_TYPE = 1;    //分组的
    public static final int CHILD_ITEM_TYPE = 2;    //组内的
    private List<ChildListBean> mList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context mContext;

    public ItemGroupChildAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * @param map
     */
    public void setList(LinkedHashMap<String, ArrayList<ChildListBean>> map) {
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            if (map.get(key).size() > 0) {
                mList.add(new ChildListBean(key, true));
            }
            mList.addAll(map.get(key));
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == GROUP_ITEM_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_group, parent, false);
            System.err.println("onCreateViewHolder");
            holder = new GroupViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_child, parent, false);
            holder = new ChildViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ChildListBean bean = mList.get(position);
        int type = holder.getItemViewType();
        if (type == GROUP_ITEM_TYPE) {
            GroupViewHolder holder1 = (GroupViewHolder) holder;
            holder1.textView.setText(bean.getChildName());
            holder1.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, bean.getChildName(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            ChildViewHolder holder1 = (ChildViewHolder) holder;
            holder1.tvChildName.setText(bean.getChildName());
            holder1.tvChildContent.setText(bean.getOpenTime());
            holder1.llContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, bean.getChildName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        System.err.println("getItemViewType");
        if (mList.get(position).isGroup()) {
            return GROUP_ITEM_TYPE;
        } else {
            return CHILD_ITEM_TYPE;
        }
    }

    /**
     * 分组
     */
    class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        public GroupViewHolder(View itemView) {
            super(itemView);
            System.err.println("GroupViewHolder");
            textView = (TextView) itemView.findViewById(R.id.tv_group_name);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    /**
     * 成员
     */
    class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView tvChildName;
        TextView tvChildContent;
        LinearLayout llContainer;

        public ChildViewHolder(View itemView) {
            super(itemView);
            tvChildName = (TextView) itemView.findViewById(R.id.tv_child_name);
            tvChildContent = (TextView) itemView.findViewById(R.id.tv_child_content);
            llContainer = itemView.findViewById(R.id.ll_child_container);
        }
    }
}
