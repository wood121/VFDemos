package com.example.wood121.viewdemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.adapter.MyAdapter;

import java.util.ArrayList;

/**
 * RecyclerView基本用法
 */
public class RecActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> datas;
    private RecyclerView recycleView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec);

        initViews();
        initDatas();

        //recycleView使用基本设置
        myAdapter = new MyAdapter(this, datas);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(myAdapter);
        Log.e("data", datas.size() + "onCreate");

        //分割线
        //点击事件
        myAdapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, String str) {
                Toast.makeText(RecActivity.this, v + ":::" + str, Toast.LENGTH_LONG).show();
            }
        });

        //添加、删除操作
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initViews() {
        Button btnAdd = (Button) findViewById(R.id.btn_add);
        Button btn_delete = (Button) findViewById(R.id.btn_delete);
        Button btn_list = (Button) findViewById(R.id.btn_list);
        Button btn_gird = (Button) findViewById(R.id.btn_gird);
        Button btn_flow = (Button) findViewById(R.id.btn_flow);

        btnAdd.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_gird.setOnClickListener(this);
        btn_flow.setOnClickListener(this);

        recycleView = (RecyclerView) findViewById(R.id.recycleView);
    }

    private void initDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("content" + i);
        }
        Log.e("data", datas.size() + "initDatas");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                myAdapter.addData(0, "头部的信息");
                recycleView.scrollToPosition(0);
                break;
            case R.id.btn_delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                myAdapter.remove(0);
                break;
            case R.id.btn_list:
                Toast.makeText(this, "list", Toast.LENGTH_SHORT).show();
                recycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                //倒序的时候我们需要指定选中的位置
//                recycleView.scrollToPosition(99);
                break;
            case R.id.btn_gird:
                Toast.makeText(this, "grid", Toast.LENGTH_SHORT).show();
                recycleView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false));
                break;
            case R.id.btn_flow:
                Toast.makeText(this, "flow", Toast.LENGTH_SHORT).show();
                recycleView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}
