package com.example.wood121.viewdemos.java;

import android.util.Log;

/**
 * author:wood121
 * Data:2018/8/1 14
 * Des:
 */
public class OperateImpl implements Operate {
    @Override
    public void OperateMethod1() {
        Log.e("wood121","invoke OperateMethod1");
        sleep(110);
    }

    @Override
    public void OperateMethod2() {
        Log.e("wood121","invoke OperateMethod2");
        sleep(110);
    }

    @Override
    public void OperateMethod3() {
        Log.e("wood121","invoke OperateMethod2");
        sleep(110);
    }

    private static void sleep(long millSeconds) {
        try {
            Thread.sleep(millSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
