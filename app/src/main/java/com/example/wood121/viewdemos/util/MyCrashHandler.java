package com.example.wood121.viewdemos.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by wood121 on 2018/3/16.
 * <p>
 * Uncaught异常发生时会终止线程，此时，系统便会通知UncaughtExceptionHandler，告诉它被终止的线程以及对应的异常，然后便会调用uncaughtException函数。
 * MyCrashHandler.getInstance().init();
 */

public class MyCrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "MyCrashHandler";
    private static MyCrashHandler handlerInstance = new MyCrashHandler();
    private final LinkedHashMap<String, String> linkedHashMap;
    private final SimpleDateFormat dateFormat;
    private Context mContext;
    private String log_path;

    public MyCrashHandler() {
        linkedHashMap = new LinkedHashMap<>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        Log.e("url", "MyCrashHandler构造函数");
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //deal this exception
        ToastUtil.showToast(mContext, "很抱歉,程序出现异常,即将退出");

        Log.e("url", "uncaughtException");
        //收集设备参数信息
        collectDeviceInfo();
        //保存日志文件到本地
        postService(saveCrashLog(throwable));
    }

    private void postService(String fileName) {
        if (fileName != null) {
            //发送崩溃日志 给服务器
        }
    }

    private String saveCrashLog(Throwable throwable) {
        Log.e("url", "saveCrashLog");

        //设备信息处理过来
        StringBuffer sbuf = new StringBuffer();
        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sbuf.append(key + "=" + value + "\n");
        }

        //存入异常信息
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);

        Throwable cause = throwable.getCause();
        if (cause != null) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();

        //异常信息拼接在后面
        String result = writer.toString();
        sbuf.append(result);

        //将异常存入文件中
        String time = dateFormat.format(new Date(System.currentTimeMillis()));
        String fileName = time + ".txt";
        File dir = new File(log_path + "/exception");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir, fileName);
        Log.e("url", "file: " + file.toString());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            String string = sbuf.toString();
            Log.e("url", "string: " + string);
            fos.write(string.getBytes());
            fos.close();
            return fileName;
        } catch (IOException e) {
            Log.e("url", "写入文件异常");
        }
        return null;
    }

    private void collectDeviceInfo() {
        Log.e("url", "collectDeviceInfo");
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                String packageName = packageInfo.applicationInfo.packageName;
                String versionName = packageInfo.versionName + "";
                String versionCode = packageInfo.versionCode + "";
                linkedHashMap.put("包名", packageName);
                linkedHashMap.put("版本名", versionName);
                linkedHashMap.put("版本号", versionCode);
            }
        } catch (Exception e) {
            Log.e("url", "收集设备信息错误" + e.toString());
            e.printStackTrace();
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                linkedHashMap.put(field.getName(), field.get(null).toString());
            } catch (IllegalAccessException e) {
                Log.e("url", "收集崩溃日志失败" + e.toString());
            }
        }
    }

    public static MyCrashHandler getInstance() {
        return handlerInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
        //存储路径
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            log_path = context.getExternalCacheDir().toString();
        } else {
            log_path = context.getCacheDir().toString();
        }
    }

}
