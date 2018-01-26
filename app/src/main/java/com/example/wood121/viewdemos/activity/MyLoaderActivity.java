package com.example.wood121.viewdemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.wood121.viewdemos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLoaderActivity extends AppCompatActivity {

    @BindView(R.id.main_rv_album)
    RecyclerView mainRvAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loader);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

    }

}
