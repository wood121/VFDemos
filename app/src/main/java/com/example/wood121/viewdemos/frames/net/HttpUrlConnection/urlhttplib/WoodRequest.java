package com.example.wood121.viewdemos.frames.net.HttpUrlConnection.urlhttplib;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @date: 2019/6/13
 * @version:
 * @author: liuzhengling
 * @des: 获取对象、配置参数、连接、获取反馈、关闭连接
 */
class WoodRequest {

    public WoodResponse requestData(String url, Map<String, String> headerMap) {
        HttpURLConnection connection = null;
        try {
            connection = getUrlHttpConnection(url, "GET");
            if (headerMap != null) {
                setHeader(connection, headerMap);
            }
            connection.connect();
            return getWoodResponse(connection);
        } catch (IOException e) {
            return getExceptonResponse(connection, e);
        }
    }

    /**
     * connection 连接失败的返回
     *
     * @param connection
     * @param e
     * @return
     */
    private WoodResponse getExceptonResponse(HttpURLConnection connection, IOException e) {
        if (connection != null) connection.disconnect();
        e.printStackTrace();
        WoodResponse woodResponse = new WoodResponse();
        woodResponse.exception = e;
        return woodResponse;
    }

    /**
     * connection 连接后获取数据
     *
     * @param connection
     * @return
     */
    private WoodResponse getWoodResponse(HttpURLConnection connection) throws IOException {
        WoodResponse woodResponse = new WoodResponse();
        woodResponse.code = connection.getResponseCode();
        woodResponse.contentLength = connection.getContentLength();
        woodResponse.inputStream = connection.getInputStream();
        woodResponse.errorStream = connection.getErrorStream();
        return woodResponse;
    }

    /**
     * @param connection
     * @param headerMap
     */
    private void setHeader(HttpURLConnection connection, Map<String, String> headerMap) {
        for (String key :
                headerMap.keySet()) {
            connection.setRequestProperty(key, headerMap.get(key));
        }
    }

    /**
     * exception，函数需要往外抛
     *
     * @param url
     * @param requestMethod get/post方式
     * @return
     */
    private HttpURLConnection getUrlHttpConnection(String url, String requestMethod) throws IOException {
        URL urlHttp = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) urlHttp.openConnection();
        urlConnection.setRequestMethod(requestMethod);
        urlConnection.setConnectTimeout(10 * 1000);
        urlConnection.setReadTimeout(15 * 1000);
        urlConnection.setDoInput(true);
        return urlConnection;
    }
}
