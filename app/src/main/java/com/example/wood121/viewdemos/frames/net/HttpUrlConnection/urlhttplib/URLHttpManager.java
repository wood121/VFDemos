package com.example.wood121.viewdemos.frames.net.HttpUrlConnection.urlhttplib;

import java.util.Map;

/**
 * @author: liuzhengling
 * @date: 2019/6/12
 * @version:
 * @des: 外观类、builder
 */
public class URLHttpManager {

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    /**
     * 静态内部类单例
     */
    private static class SingleTonHolder {
//        ViewPager
//        FragmentPagerAdapter
        private static URLHttpManager instance = new URLHttpManager();
    }

    public static URLHttpManager getInstance() {
        return SingleTonHolder.instance;
    }

    /**
     * url、参数parms、header参数、回调参数
     * Request,
     * RealRequest,
     * RealResponse
     */
    public void get(String url, Map<String, String> paramsMap, Map<String, String> headerMap, HttpCallBack callBack) {
        new Request(METHOD_GET, url, paramsMap, headerMap, callBack).excute();
    }


}
