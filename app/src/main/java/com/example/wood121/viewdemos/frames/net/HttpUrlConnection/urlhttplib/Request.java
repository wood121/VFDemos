package com.example.wood121.viewdemos.frames.net.HttpUrlConnection.urlhttplib;


import java.net.HttpURLConnection;
import java.util.Map;

/**
 * @des:
 * @author: liuzhengling
 * @date: 2019/6/13
 * @version:
 */
class Request {
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private Thread mThread;

    public Request(String method, String url, Map<String, String> paramsMap, Map<String, String> headerMap, HttpCallBack callBack) {
        switch (method) {
            case METHOD_GET:
                urlHttpGet(url, paramsMap, headerMap, callBack);
                break;
            case METHOD_POST:
                break;
        }
    }

    /**
     * @param url
     * @param paramsMap
     * @param headerMap
     * @param callBack
     */
    private void urlHttpGet(String url, Map<String, String> paramsMap, Map<String, String> headerMap, HttpCallBack callBack) {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //真正执行数据请求任务：
                WoodResponse woodResponse = new WoodRequest().requestData(getUrl(url, paramsMap), headerMap);
                if (woodResponse.code == HttpURLConnection.HTTP_OK) {
                    callBack.onSuccess(woodResponse);
                } else {
                    callBack.onError(woodResponse);
                }
            }
        });
    }

    /**
     * 通过Thread任务，一个任务就开一个线程执行
     */
    public void excute() {
        if (mThread != null) {
            mThread.start();
        }
    }

    /**
     * 拼接get的url
     *
     * @param url
     * @param paramsMap
     * @return https://github.com/search?l=Java&o=desc&q=Retrofit&s=stars&type=Repositories
     */
    private String getUrl(String url, Map<String, String> paramsMap) {
        if (paramsMap != null) {
            url = url + "?";
            for (String key : paramsMap.keySet()) {
                url = url + key + "=" + paramsMap.get(key) + "&";
            }
            url = url.substring(0, url.length() - 1);   //去掉&尾部
        }

        return url;
    }


}
