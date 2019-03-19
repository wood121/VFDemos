package com.example.wood121.viewdemos.apis.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class LocalBroadcastActivity extends BaseActivity {


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_local_broadcast;
    }

    @Override
    protected void initPageViewListener() {
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        lbm.registerReceiver(receiver, new IntentFilter("'LOCAL_ACTION'"));
        lbm.sendBroadcast(new Intent("LOCAL_ACTION"));
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
