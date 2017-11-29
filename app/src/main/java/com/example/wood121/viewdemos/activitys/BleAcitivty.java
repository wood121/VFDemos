package com.example.wood121.viewdemos.activitys;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wood121.viewdemos.R;
import com.joker.annotation.PermissionsCustomRationale;
import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsNonRationale;
import com.joker.annotation.PermissionsRationale;
import com.joker.api.Permissions4M;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BleAcitivty extends AppCompatActivity {

    private static final int LOCATIION_CODE = 1;
    @InjectView(R.id.tv_ble)
    TextView tvBle;
    private BluetoothAdapter mBluetoothAdapter;
    private List<BluetoothDevice> mListDevices = new ArrayList<>();
    private BroadcastReceiver boadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.e("awood", device.getName() + ":" + device.getAddress());

                mListDevices.add(device);
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                Log.i("iii", "wa, 设备扫描开始啦...");
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                Log.i("iii", "ok, 设备扫描终于结束了，好累...");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_acitivty);
        ButterKnife.inject(this);
        //蓝牙适配器初始化
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //蓝牙权限准备
    private void prepareBle() {
        if (mBluetoothAdapter == null) {
            Toast.makeText(BleAcitivty.this, "设备不支持蓝牙", Toast.LENGTH_SHORT).show();
        } else {
            //启动蓝牙
            openBle();
        }
    }

    private void openBle() {
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        }
        //设置蓝牙搜索
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);   //发现设备
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED); //扫描开始
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);  //扫描结束
        registerReceiver(boadcastReceiver, filter);

        //开启蓝牙扫描
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        mBluetoothAdapter.startDiscovery();
    }

    @OnClick(R.id.tv_ble)
    public void onViewClicked() {
//        Toast.makeText(this, "蓝牙权限按钮", Toast.LENGTH_LONG).show();
        Permissions4M.get(BleAcitivty.this)
                .requestForce(true)
                .requestPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .requestCodes(LOCATIION_CODE)
                .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                .request();
    }

    @PermissionsGranted(LOCATIION_CODE)
    public void granted() {
        Toast.makeText(this, "PermissionsGranted", Toast.LENGTH_LONG).show();
        prepareBle();
    }

    @PermissionsDenied(LOCATIION_CODE)
    public void denied() {
        Toast.makeText(this, "PermissionsDenied", Toast.LENGTH_LONG).show();
    }

    @PermissionsRationale(LOCATIION_CODE)
    public void rationale() {
        Toast.makeText(this, "PermissionsRationale", Toast.LENGTH_LONG).show();
    }

    @PermissionsCustomRationale(LOCATIION_CODE)
    public void cusRationale() {
        new AlertDialog.Builder(this)
                .setMessage("读取地理位置权限申请：\n我们需要您开启读取地理位置权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Permissions4M.get(BleAcitivty.this)
                                // 注意添加 .requestOnRationale()
                                .requestForce(true)
                                .requestOnRationale()
                                .requestPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                                .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                                .requestCodes(LOCATIION_CODE)
                                .request();
                    }
                })
                .show();
    }

    /**
     * @param intent 由requestPageType确定是跳转手机关机还是手机原生
     */
    @PermissionsNonRationale({LOCATIION_CODE})
    public void nonRationale(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBluetoothAdapter.cancelDiscovery();
        unregisterReceiver(boadcastReceiver);
        mBluetoothAdapter.disable();
    }
}
