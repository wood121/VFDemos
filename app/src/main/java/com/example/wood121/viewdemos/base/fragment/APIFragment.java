package com.example.wood121.viewdemos.base.fragment;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.api_part.AsynTaskDemoActivity;
import com.example.wood121.viewdemos.api_part.BlueToothActivity;
import com.example.wood121.viewdemos.api_part.BookManagerActivity;
import com.example.wood121.viewdemos.api_part.ChatActivity;
import com.example.wood121.viewdemos.view_part.ContextActivity;
import com.example.wood121.viewdemos.api_part.DataStorageActivity;
import com.example.wood121.viewdemos.api_part.ExecutorActivity;
import com.example.wood121.viewdemos.api_part.LamadaActivity;
import com.example.wood121.viewdemos.api_part.ServiceActivity;
import com.example.wood121.viewdemos.api_part.database.SqliteActivity;
import com.example.wood121.viewdemos.api_part.VideoActivity;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;
import com.example.wood121.viewdemos.datebase_device.SqliteDeviceActivity;
import com.example.wood121.viewdemos.mine_part.MessengerActivity;

/**
 * Created by wood121 on 2017/12/21.
 * Android api调用情况
 */

public class APIFragment extends BaseTabContentFragment {

    public static APIFragment apiFragment;

    public static APIFragment newInstance() {
        if (apiFragment == null) {
            synchronized (APIFragment.class) {
                if (apiFragment == null) {
                    apiFragment = new APIFragment();
                }
            }
        }
        return apiFragment;
    }

    @Override
    protected void getFragDatas() {
        mData.add(new ModelRecyclerBean("蓝牙扫描", R.mipmap.circle_captcha, BlueToothActivity.class));
        mData.add(new ModelRecyclerBean("MediaPlayer", R.mipmap.circle_captcha, VideoActivity.class));
        mData.add(new ModelRecyclerBean("Service", R.mipmap.circle_captcha, ServiceActivity.class));
        mData.add(new ModelRecyclerBean("数据存储", R.mipmap.circle_captcha, DataStorageActivity.class));
        mData.add(new ModelRecyclerBean("AIDL客户端", R.mipmap.circle_captcha, BookManagerActivity.class));
        mData.add(new ModelRecyclerBean("AsynTask", R.mipmap.circle_captcha, AsynTaskDemoActivity.class));
        mData.add(new ModelRecyclerBean("ThreadPoolExecutor", R.mipmap.circle_captcha, ExecutorActivity.class));
        mData.add(new ModelRecyclerBean("Lamada", R.mipmap.circle_captcha, LamadaActivity.class));
        mData.add(new ModelRecyclerBean("messenger", R.mipmap.circle_captcha, MessengerActivity.class));
        mData.add(new ModelRecyclerBean("udp_chat", R.mipmap.circle_captcha, ChatActivity.class));
        mData.add(new ModelRecyclerBean("TableBook_DBManager", R.mipmap.circle_captcha, SqliteActivity.class));
        mData.add(new ModelRecyclerBean("TableVirtualidDeviceBean_DBManager", R.mipmap.circle_captcha, SqliteDeviceActivity.class));
        }
}
