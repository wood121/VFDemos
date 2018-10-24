package com.example.wood121.viewdemos.view_part;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wood121.viewdemos.R;

import java.util.ArrayList;

public class ViewActionRecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_action_rec);

        RecyclerView actionRecyclerView = findViewById(R.id.action_recycleView);

        ArrayList<String> list = new ArrayList<>();
        list.add("111111");
        list.add("222222");
        list.add("333333");
        list.add("444444");
        list.add("555555");
        list.add("666666");
        list.add("777777");
        list.add("888888");
        list.add("999999");
        list.add("101010");
        list.add("121212");
        list.add("131313");
        list.add("141414");
        list.add("151515");
        list.add("161616");
        list.add("171717");
        list.add("181818");
        list.add("191919");
        list.add("202020");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        actionRecyclerView.setLayoutManager(linearLayoutManager);
        RecActionAdapter recActionAdapter = new RecActionAdapter(this, list);
        actionRecyclerView.setAdapter(recActionAdapter);

        actionRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
