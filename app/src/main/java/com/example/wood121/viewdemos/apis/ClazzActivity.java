package com.example.wood121.viewdemos.apis;

import android.os.Bundle;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ClazzActivity extends BaseActivity {

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_clazz;
    }

    @Override
    protected void initPageViewListener() {
//        getTheClass();
    }

    private void getTheClass() throws ClassNotFoundException {
        Class.forName("com.cangwang");
        Class<Class> clazz = Class.class;

        try {
            Class aClass = clazz.newInstance();
            Constructor<Class> constructor = clazz.getConstructor();
            Class aClass1 = constructor.newInstance();

            Field[] declaredFields = clazz.getDeclaredFields();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
