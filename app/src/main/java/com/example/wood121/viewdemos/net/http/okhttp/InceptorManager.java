package com.example.wood121.viewdemos.net.http.okhttp;

/**
 * @author wood121
 * @desc
 * @time:2018/8/8
 */
public class InceptorManager {

    private static InceptorManager sInceptorManager;

    private InceptorManager() {
    }

    public static InceptorManager getInstance(){
        if(sInceptorManager == null){
            synchronized (InceptorManager.class){
                if(sInceptorManager == null){
                    sInceptorManager = new InceptorManager();
                }
            }
        }
        return sInceptorManager;
    }



}
