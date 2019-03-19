package com.example.wood121.viewdemos.apis.annotation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

import java.lang.reflect.Field;

/**
 * 1.运行时注解、编译时注解
 * 2.AnnotationProcessor
 * 3.
 */
public class AnnotationActivity extends BaseActivity implements View.OnClickListener {

    @getViewTo(R.id.tv_wood)
    private TextView mTv;

    @getViewTo(R.id.btn_wood)
    private TextView mBtn;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_annotation;
    }

    @Override
    protected void initPageViewListener() {
        getAllAnnotationView();

        mBtn.setOnClickListener(this);
    }

    private void getAllAnnotationView() {
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field field :
                declaredFields) {
            try {
                if (field.getAnnotations() != null) {
                    if (field.isAnnotationPresent(getViewTo.class)) {
                        field.setAccessible(true);
                        getViewTo annotation = field.getAnnotation(getViewTo.class);
                        field.set(this, findViewById(annotation.value()));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_wood:
                mBtn.setText("这是我修改了");
                break;
        }
    }
}
