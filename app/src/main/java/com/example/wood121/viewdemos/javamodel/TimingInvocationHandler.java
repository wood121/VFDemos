package com.example.wood121.viewdemos.javamodel;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * author:wood121
 * Data:2018/8/1 14
 * Des:InvocationHandler负责链接代理类和委托类的中间类必须实现的接口
 */
public class TimingInvocationHandler implements InvocationHandler {

    //委托类对象
    private Object target;

    public TimingInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * @param proxy 通过proxy.newProxyInstance生成的代理类对象
     * @param method 表示代理对象被调用的函数
     * @param args 表示代理对象被调用的函数的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object obj = method.invoke(target, args);

        long start = System.currentTimeMillis();
        Log.e("wood121", method.getName() + "cost time is:" + (System.currentTimeMillis() - start));
        return obj;
    }
}
