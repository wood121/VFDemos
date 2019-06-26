package com.example.wood121.viewdemos.frames.net.HttpUrlConnection;

import java.io.InputStream;

/**
 * @des:
 * @author: liuzhengling
 * @date: 2019/6/12
 * @version:
 */
public class RealResponse {
    public InputStream inputStream;
    public InputStream errorStream;
    public int code;
    public long contentLength;
    public Exception exception;
}
