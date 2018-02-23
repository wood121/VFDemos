package com.example.wood121.viewdemos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wood121.viewdemos.BaseActivity;
import com.example.wood121.viewdemos.R;

public class GetAddressActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_get_address;
    }

    @Override
    protected void initPageViewListener() {
        TextView getAddress = (TextView) findViewById(R.id.tv_getAddress);
        getAddress.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(this, AddressActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_OK) {
            if (requestCode == 1) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
