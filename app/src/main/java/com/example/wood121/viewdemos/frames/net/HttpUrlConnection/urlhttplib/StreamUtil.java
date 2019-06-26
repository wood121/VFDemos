package com.example.wood121.viewdemos.frames.net.HttpUrlConnection.urlhttplib;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @date: 2019/6/13
 * @version:
 * @author: liuzhengling
 * @des: 字节流转字符串
 */
public class StreamUtil {

    public static String getStreamString(InputStream inputStream) {
        String buf;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            buf = sb.toString();
            return buf;
        } catch (Exception e) {
            return null;
        }
    }
}
