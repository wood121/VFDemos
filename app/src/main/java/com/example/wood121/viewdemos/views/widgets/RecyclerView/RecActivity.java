package com.example.wood121.viewdemos.views.widgets.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wood121.viewdemos.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * RecyclerView基本用法
 */
public class RecActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> datas;
    private RecyclerView recycleView;
    private RecItemAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec);

        initViews();
        initDatas();

        //recycleView使用基本设置
        myAdapter = new RecItemAdapter(this, datas);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(myAdapter);
        Log.e("data", datas.size() + "onCreate");

        //item增加、删除操作的动画
        recycleView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //点击事件
        myAdapter.setOnItemClickListener(new RecItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, String str) {
                Toast.makeText(RecActivity.this, v + ":::" + str, Toast.LENGTH_LONG).show();
            }
        });
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
                recycleView.setLayoutManager(new LinearLayoutManager(this));
                //倒序的时候我们需要指定选中的位置
//                recycleView.scrollToPosition(99);
                break;
            case R.id.btn_gird:
                Toast.makeText(this, "grid", Toast.LENGTH_SHORT).show();
                recycleView.setLayoutManager(new GridLayoutManager(this, 3));
                //GridLayoutManager的时候，分割线不一样。
                recycleView.addItemDecoration(new DividerGridItemDecoration(this));
                break;
            case R.id.btn_flow:
                Toast.makeText(this, "flow", Toast.LENGTH_SHORT).show();
                recycleView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}
