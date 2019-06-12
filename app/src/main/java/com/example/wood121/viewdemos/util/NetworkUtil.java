package com.example.wood121.viewdemos.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

public class NetworkUtil {
    public static int NET_CNNT_BAIDU_OK = 1;
    public static int NET_CNNT_BAIDU_TIMEOUT = 2;
    public static int NET_NOT_PREPARE = 3;
    public static int NET_ERROR = 4;
    private static int TIMEOUT = 3000;

    public NetworkUtil() {
    }

    public static boolean isConnected(Context context) {
        return isNetworkAvailable(context);
    }

    public static boolean isNetworkAvailable(Context context) {
        @SuppressLint("WrongConstant") ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        if (null == manager) {
            return false;
        } else {
            NetworkInfo info = manager.getActiveNetworkInfo();
            return null != info && info.isAvailable();
        }
    }

    public static String getLocalIpAddress() {
        String ret = "";

        try {
            Enumeration en = NetworkInterface.getNetworkInterfaces();

            while (en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                Enumeration enumIpAddr = intf.getInetAddresses();

                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ret = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException var5) {
            var5.printStackTrace();
        }

        return ret;
    }

    public static int getNetState(Context context) {
        try {
            @SuppressLint("WrongConstant") ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivity != null) {
                NetworkInfo networkinfo = connectivity.getActiveNetworkInfo();
                if (networkinfo != null) {
                    if (networkinfo.isAvailable() && networkinfo.isConnected()) {
                        if (!connectionNetwork()) {
                            return NET_CNNT_BAIDU_TIMEOUT;
                        }

                        return NET_CNNT_BAIDU_OK;
                    }

                    return NET_NOT_PREPARE;
                }
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return NET_ERROR;
    }

    public static boolean connectionNetwork() {
        boolean result = false;
        HttpURLConnection httpUrl = null;

        try {
            httpUrl = (HttpURLConnection) (new URL("http://www.baidu.com")).openConnection();
            httpUrl.setConnectTimeout(TIMEOUT);
            httpUrl.connect();
            result = true;
        } catch (IOException var6) {

        } finally {
            if (null != httpUrl) {
                httpUrl.disconnect();
            }

        }

        return result;
    }

    public static boolean is3G(Context context) {
        @SuppressLint("WrongConstant") ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.getType() == 0;
    }

    public static boolean isWifi(Context context) {
        @SuppressLint("WrongConstant") ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.getType() == 1;
    }

    public static boolean is2G(Context context) {
        @SuppressLint("WrongConstant") ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null && (activeNetInfo.getSubtype() == 2 || activeNetInfo.getSubtype() == 1 || activeNetInfo.getSubtype() == 4);
    }

    public static boolean isWifiEnabled(Context context) {
        @SuppressLint("WrongConstant") ConnectivityManager mgrConn = (ConnectivityManager) context.getSystemService("connectivity");
        @SuppressLint("WrongConstant") TelephonyManager mgrTel = (TelephonyManager) context.getSystemService("phone");
        return mgrConn.getActiveNetworkInfo() != null && mgrConn.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED || mgrTel.getNetworkType() == 3;
    }

    public static boolean isInternet() {
        String checkUrl = "https://www.baidu.com";
        boolean SOCKET_TIMEOUT_MS = true;
        HttpURLConnection connection = null;

        boolean var4;
        try {
            URL url = new URL("https://www.baidu.com");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(false);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setUseCaches(false);
            connection.connect();
            var4 = connection.getResponseCode() == 200;
            return var4;
        } catch (Exception var8) {
            var4 = false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }

        return var4;
    }
}
