package com.example.wood121.viewdemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.wood121.viewdemos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RxjavaRetrofitActivity extends AppCompatActivity {

    @BindView(R.id.btn_kwy)
    Button btnKwy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_retrofit);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_kwy)
    public void onViewClicked() {


    }
}
