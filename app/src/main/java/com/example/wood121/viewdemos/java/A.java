package com.example.wood121.viewdemos.java;

/**
 * author:wood121
 * Data:2018/8/1 14
 * Des: 静态代理的类型
 */
public class A {

    private B mB;

    public A(B b) {
        mB = b;
    }

    public void method1() {
        mB.method1();
    }

    public void method2() {
        mB.method2();
    }

    public void method3() {
        mB.method2();
    }
}

class B {
    public void method1() {
    }

    public void method2() {
    }

    public void method3() {
    }
}
