package com.example.wood121.viewdemos.apis.database_.sqlite_device;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.apis.database_.sqlite_device.bean.VirtualidDeviceBean;
import com.example.wood121.viewdemos.util.BytesUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class SqliteDeviceActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SqliteDeviceActivity";
    private static final int INSERT_ONE = 101;
    private static final int QUERY_ONE = 102;
    private ArrayList<VirtualidDeviceBean> mList;
    private RecDeviceAdapter mRecAdapter;
    private EditText mEtInsertNumbers;
    private RadioButton mRbSingle;
    private RadioButton mRbWhole;
    private RadioGroup mRgWhether;
    private int checkType;
    private EditText mEtDeviceId;
    private EditText mEtMacAddress;
    private EditText mEtNetworkDeviceId;
    private EditText mEtMeshId;
    private EditText mEtPhoneId;
    private EditText mEtVirtualId;
    private String mNativeNetworkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        mNativeNetworkId = "D9F14F";

        mEtDeviceId = findViewById(R.id.et_deviceId);
        mEtMacAddress = findViewById(R.id.et_macAddress);
        mEtNetworkDeviceId = findViewById(R.id.et_networkDeviceId);
        mEtMeshId = findViewById(R.id.et_meshId);
        mEtPhoneId = findViewById(R.id.et_phoneId);
        mEtVirtualId = findViewById(R.id.et_virtualId);

        mEtInsertNumbers = findViewById(R.id.et_insert_numbers);

        findViewById(R.id.btn_sqliteadd).setOnClickListener(this);
        findViewById(R.id.btn_sqlitedelete).setOnClickListener(this);
        findViewById(R.id.btn_sqliteupdate).setOnClickListener(this);
        findViewById(R.id.btn_sqlitequery).setOnClickListener(this);
        findViewById(R.id.btn_bat_insert).setOnClickListener(this);
        findViewById(R.id.btn_cal_VirtualId).setOnClickListener(this);

        mRbSingle = findViewById(R.id.rb_single);
        mRbWhole = findViewById(R.id.rb_whole);
        mRgWhether = findViewById(R.id.rg_whether);
        mRbSingle.setChecked(true);
        mRgWhether.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_single:
                        checkType = 0;
                        break;
                    case R.id.rb_whole:
                        checkType = 1;
                        break;
                }
            }
        });

        RecyclerView recView = findViewById(R.id.rec_sqlite);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(linearLayoutManager);
        mRecAdapter = new RecDeviceAdapter();
        recView.setAdapter(mRecAdapter);

        mList = new ArrayList<>();
        getAllDeviceBean();
        mRecAdapter.setData(mList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sqliteadd:
                add();
                break;
            case R.id.btn_sqlitedelete:
                if (checkType == 0) {
                    delete();
                } else if (checkType == 1) {
                    removeAll();
                }
                break;
            case R.id.btn_sqliteupdate:
                update();
                break;
            case R.id.btn_sqlitequery:
                query();
                break;
            case R.id.btn_bat_insert:
                batInsert();
                break;
            case R.id.btn_cal_VirtualId:
                try {
                    calculateVirtualId();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void calculateVirtualId() throws IOException {
        //分组
        HashMap<Integer, List<VirtualidDeviceBean>> hashMap = new HashMap<>();
        for (VirtualidDeviceBean deviceBean :
                mList) {
            int phoneId = deviceBean.getPhoneId();
            List<VirtualidDeviceBean> beanList = hashMap.get(phoneId);
            if (beanList == null) {
                beanList = new ArrayList<>();
                beanList.add(deviceBean);
                hashMap.put(phoneId, beanList);
            } else {
                beanList.add(deviceBean);
            }
        }

        mList.clear();
        //排序
        for (Integer integer :
                hashMap.keySet()) {
            List<VirtualidDeviceBean> virtualidDeviceBeans = hashMap.get(integer);
            Collections.sort(virtualidDeviceBeans, new Comparator<VirtualidDeviceBean>() {
                @Override
                public int compare(VirtualidDeviceBean o1, VirtualidDeviceBean o2) {
                    return o1.getMeshId() - o2.getMeshId();
                }
            });

            int virtualMeshId = 1;
            //映射
            for (VirtualidDeviceBean deviceBean :
                    virtualidDeviceBeans) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2);
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                int phoneId = deviceBean.getPhoneId();
                dataOutputStream.writeByte(phoneId);
                dataOutputStream.writeByte(virtualMeshId);
                if (virtualMeshId > 255) {
                    virtualMeshId = 1;
                } else {
                    virtualMeshId++;
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                Log.e(TAG, "phoneId:" + phoneId + ",|||==virtualMeshId:" + virtualMeshId + ",|||==bind 2 to one:" + BytesUtil.bytesToHexString(bytes));
                deviceBean.setVirtualId(BytesUtil.byteArrayToInt(bytes));
            }

            mList.addAll(virtualidDeviceBeans);
        }
        mRecAdapter.setData(mList);
    }

    //批量插入数据，插入的数据是没有虚拟id的，需要我这边进行计算
    private void batInsert() {
        String insertNumbers = mEtInsertNumbers.getText().toString().trim();

        String deviceId = mEtDeviceId.getText().toString().trim();
        String macAddress = mEtMacAddress.getText().toString().trim();
        String networkId = mEtNetworkDeviceId.getText().toString().trim();
        String meshId = mEtMeshId.getText().toString().trim();
        String phoneId = mEtPhoneId.getText().toString().trim();
        String virtualId = mEtVirtualId.getText().toString().trim();

        int meshIdInt = Integer.parseInt(meshId);
        int phoneIdInt = Integer.parseInt(phoneId);
        ArrayList<VirtualidDeviceBean> list = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(insertNumbers); i++) {
            VirtualidDeviceBean deviceBean = new VirtualidDeviceBean(deviceId, macAddress, networkId, meshIdInt, Integer.parseInt(phoneId), 0);
            if (phoneIdInt % 2 == 0) {
                meshIdInt++;
            } else {
                meshIdInt--;
            }
            list.add(deviceBean);
        }

        //可以继续批量查询
        DBDeviceManager.getInstence(this).executeTranStateInsert(list);
        //获取表中的所有数据
        getAllDeviceBean();
        mRecAdapter.setData(mList);
    }

    //查询某个字段
    private void query() {
        String virtualId = mEtVirtualId.getText().toString().trim();
        VirtualidDeviceBean deviceBean = DBDeviceManager.getInstence(this).getPhoneIdAndMeshId(Integer.parseInt(virtualId));
        mList.clear();
        mList.add(deviceBean);
        mRecAdapter.setData(mList);
    }

    //修改数据
    private void update() {
        getAllDeviceBean();
        mRecAdapter.setData(mList);
    }

    //删除一条
    private void delete() {
        String phoneId = mEtPhoneId.getText().toString().trim();
        boolean deleteFlag = DBDeviceManager.getInstence(this).deleteDeviceBean(Integer.parseInt(phoneId));
        Log.e("wood", "deleteFlag:" + deleteFlag);
        getAllDeviceBean();
        mRecAdapter.setData(mList);
    }

    //删除所有数据
    private void removeAll() {
        boolean removeAllFlag = DBDeviceManager.getInstence(this).removeAll();
        Log.e("wood", "deleteFlag:" + removeAllFlag);
        getAllDeviceBean();
        mRecAdapter.setData(mList);
    }

    //插入一条数据
    private void add() {
        String deviceId = mEtDeviceId.getText().toString().trim();
        String macAddress = mEtMacAddress.getText().toString().trim();
        String networkId = mEtNetworkDeviceId.getText().toString().trim();
        String meshId = mEtMeshId.getText().toString().trim();
        String phoneId = mEtPhoneId.getText().toString().trim();
        String virtualId = mEtVirtualId.getText().toString().trim();

        VirtualidDeviceBean deviceBean = new VirtualidDeviceBean(deviceId, macAddress, networkId, Integer.parseInt(meshId), Integer.parseInt(phoneId), 0);

        VirtualidDeviceBean virtualidDeviceBean = DBDeviceManager.getInstence(this).insertVirtualidDeviceBean(deviceBean);
        insertData(virtualidDeviceBean);
    }

    //将新插入的数据查询出来添加到列表
    private void insertData(VirtualidDeviceBean virtualidDeviceBean) {
        mList.add(virtualidDeviceBean);
        mRecAdapter.setData(mList);
    }

    //查询表中所有数据
    private void getAllDeviceBean() {
        mList.clear();
        mList.addAll(DBDeviceManager.getInstence(this).getAllBooks());
    }
}
