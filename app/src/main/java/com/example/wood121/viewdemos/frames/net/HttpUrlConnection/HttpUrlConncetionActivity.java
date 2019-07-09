package com.example.wood121.viewdemos.frames.net.HttpUrlConnection;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

import java.io.File;
import java.util.HashMap;

public class HttpUrlConncetionActivity extends BaseActivity {

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_http_url_conncetion;
    }

    @Override
    protected void initEvent() {
//        postData();

        String url = "http://200.200.200.58:8981/group1/M00/0D/C9/yMjIOllsl32AbKETAAIEbfMlCcs57.json";
        UrlHttpUtil.downloadFile(url, new CallBack.CallBackFile(Environment.getExternalStorageDirectory().getAbsolutePath(), "lzl.txt") {
            @Override
            public void onFailure(int code, String errorMessage) {

            }

            @Override
            public void onResponse(File response) {
                Log.e("wood121", "路径:" + response.getAbsolutePath() + ",size " + response.length());
            }
        });
    }

    private void getData() {
        String url = "https://www.baidu.com/";
        UrlHttpUtil.get(url, new CallBack.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {

            }

            @Override
            public void onResponse(String response) {
                Toast.makeText(HttpUrlConncetionActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Log.d("kwwl", response);
            }
        });
    }


    private void postData() {
        String url = "https://www.baidu.com/";
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("title", "title");
        paramsMap.put("desc", "desc");
        UrlHttpUtil.post(url, paramsMap, new CallBack.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {

            }

            @Override
            public void onResponse(String response) {
                Toast.makeText(HttpUrlConncetionActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Log.d("kwwl", response);
            }
        });

    }

}

