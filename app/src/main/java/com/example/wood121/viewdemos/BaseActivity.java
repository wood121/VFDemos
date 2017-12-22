package com.example.wood121.viewdemos;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.wood121.viewdemos.util.StyleManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wood121 on 2017/12/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private StyleManager styleManager;
    private Unbinder unbinder;
    private static final int PERMISSION_REQUEST_CODE = 0;


    /**
     * 需要进行检测的权限数组
     */
    protected String[] mNeedPermissions = {
            // 这里填你需要申请的权限
            // 如：读取sd卡
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //statusBar设置
        styleManager = StyleManager.getInstance(this);
        styleManager.setStatusBarStyle(this, getResources().getColor(R.color.green_6f3));

        checkPermissions(mNeedPermissions);

        // 业务逻辑处理
        initData(savedInstanceState);
        // 设置layout布局
        setContentView(initPageLayoutId());
        // 初始化页面控件点击
        initPageViewListener();
    }

    //检测所需权限是否满足
    private void checkPermissions(String... mNeedPermissions) {
        List<String> needRequestPermissionList = findDeniedPermissions(mNeedPermissions);
        if (null != needRequestPermissionList
                && needRequestPermissionList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissionList.toArray(
                            new String[needRequestPermissionList.size()]),
                    PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
                needRequestPermissionList.add(perm);
            }
        }
        return needRequestPermissionList;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (!verifyPermissions(grantResults)) {
                showMissingPermissionDialog();
            }
        }
    }

    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示信息
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("需要权限");

        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 启动应用的设置
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract int initPageLayoutId();

    protected abstract void initPageViewListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
