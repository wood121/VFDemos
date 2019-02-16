package com.example.wood121.viewdemos.apis;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.apis.audio_video.VideoActivity;
import com.example.wood121.viewdemos.apis.data_storage.DataStorageActivity;
import com.example.wood121.viewdemos.apis.database_.DBActivity;
import com.example.wood121.viewdemos.apis.ipc.IpcActivity;
import com.example.wood121.viewdemos.apis.threads.ThreadsActivity;
import com.example.wood121.viewdemos.base.bean.ModelRecyclerBean;
import com.example.wood121.viewdemos.base.fragments.BaseTabContentFragment;

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
        mData.add(new ModelRecyclerBean("数据存储", R.mipmap.circle_seek, DataStorageActivity.class));
        mData.add(new ModelRecyclerBean("数据库", R.mipmap.circle_seek, DBActivity.class));
        mData.add(new ModelRecyclerBean("进程通信", R.mipmap.circle_seek, IpcActivity.class));
        mData.add(new ModelRecyclerBean("多线程", R.mipmap.circle_seek, ThreadsActivity.class));
        mData.add(new ModelRecyclerBean("MediaPlayer", R.mipmap.circle_seek, VideoActivity.class));
        mData.add(new ModelRecyclerBean("Lamada", R.mipmap.circle_seek, LamadaActivity.class));
    }
}
