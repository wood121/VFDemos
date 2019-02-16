package com.example.wood121.viewdemos.frames.wireless_iot;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

/**
 * 1.蓝牙权限ble,admin,ACCESS_FINE_LOCATION；蓝牙开启；蓝牙扫描
 * 2.蓝牙通信：字节指令
 */
public class BlueToothActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int LOCATIION_CODE = 1;

//    @BindView(R.id.tv_ble)
//    TextView tvBle;
//    @BindView(R.id.tv_ble_byte)
//    TextView tvBleByte;

    private BluetoothAdapter mBluetoothAdapter;
    private List<BluetoothDevice> mListDevices = new ArrayList<>();
    private BroadcastReceiver boadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceAddress = device.getAddress();
                Log.e("awood", deviceName + ":" + deviceAddress);
                if (deviceName != null && deviceName.startsWith("ekmk")) {
                    Toast.makeText(BlueToothActivity.this, deviceName, Toast.LENGTH_SHORT).show();
                    mBluetoothAdapter.cancelDiscovery();
                }
                mListDevices.add(device);
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                Log.i("iii", "ok, 设备扫描开始");
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                Log.i("iii", "ok, 设备扫描结束");
            }
        }
    };
    private boolean isRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_acitivty);
        ButterKnife.bind(this);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        findViewById(R.id.tv_ble).setOnClickListener(this);
        findViewById(R.id.tv_ble_byte).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //蓝牙权限准
    private void prepareBle() {
        Log.e("awood", Thread.currentThread().getName());
        if (mBluetoothAdapter == null) {
            Toast.makeText(BlueToothActivity.this, "设备不支持蓝牙", Toast.LENGTH_SHORT).show();
        } else {
            //启动蓝牙
            openBle();
        }
    }

    public static final int REQUEST_CODE_BLUETOOTH_ON = 12;

    private void openBle() {
        if (!mBluetoothAdapter.isEnabled()) {
            // 请求打开 Bluetooth
            Intent requestBluetoothOn = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            // 请求开启 Bluetooth
            this.startActivityForResult(requestBluetoothOn,
                    REQUEST_CODE_BLUETOOTH_ON);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_BLUETOOTH_ON) {
            switch (resultCode) {
                case Activity.RESULT_OK: {
                    startBleScan();
                }
                break;
                case Activity.RESULT_CANCELED: {

                }
                break;
                default:
                    break;
            }
        }
    }

    private void startBleScan() {
        //设置蓝牙搜索
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);   //发现设备
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED); //扫描开始
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);  //扫描结束
        registerReceiver(boadcastReceiver, filter);
        isRegistered = true;
        //开启蓝牙扫描
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        mBluetoothAdapter.startDiscovery();
    }


    @PermissionsGranted(LOCATIION_CODE)
    public void granted() {
        Log.e("awood", Thread.currentThread().getName());
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
                        Permissions4M.get(BlueToothActivity.this)
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
        mBluetoothAdapter.disable();
        if (isRegistered) {
            unregisterReceiver(boadcastReceiver);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ble:
                Log.e("awood", Thread.currentThread().getName());
                Permissions4M.get(BlueToothActivity.this)
                        .requestForce(true)
                        .requestPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                        .requestCodes(LOCATIION_CODE)
                        .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                        .request();
                break;
            case R.id.tv_ble_byte:
//                Intent intent = new Intent(this, BleBytesActivity.class);
//                startActivity(intent);
                break;
        }
    }
}
