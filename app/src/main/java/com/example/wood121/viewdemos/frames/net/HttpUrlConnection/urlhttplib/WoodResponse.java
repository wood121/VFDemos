package com.example.wood121.viewdemos.frames.net.HttpUrlConnection.urlhttplib;

import java.io.InputStream;

/**
 * @date: 2019/6/13
 * @version:
 * @author: liuzhengling
 * @des:
 */
class WoodResponse {
    public int code;
    public int contentLength;
    public InputStream inputStream;
    public InputStream errorStream;
    public Exception exception;
}
