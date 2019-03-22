package com.example.wood121.viewdemos.apis.patterns;


/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/3/21 17:05<br>
 * 版本： v2.0<br>
 */
public class Singleton {
    /**
     * 静态内部类的单例模式
     *
     * @return
     */
    public static Singleton getInstance() {
        return InstanceHolder.mInstance;
    }

    static class InstanceHolder {
        private static Singleton mInstance = new Singleton();
    }
}
