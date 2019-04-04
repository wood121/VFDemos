package com.example.wood121.viewdemos.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/4/4 15:27<br>
 * 版本： v2.0<br>
 */
public class SharePreferencesUtil {

    public static final int KEY_NOT_FOUND = -1;
    public static final int INT_KEY_NOT_FOUND = -99999;
    public static final long LONg_KEY_NOT_FOUND = 0L;
    public static final String STRING_KEY_NOT_FOUND = null;
    public static final boolean BOOLEAN_KEY_NOT_FOUND = false;
    public static final String APP_PREFERENCE = "app";

    private SharePreferencesUtil() {
    }

    private static SharedPreferences getAppPreferences(Context context) {
        SharedPreferences appPreferences = context.getSharedPreferences("app", 0);
        return appPreferences;
    }

    public static void removeKey(Context context, String strKey) {
        SharedPreferences appPreferences = getAppPreferences(context);
        SharedPreferences.Editor editor = appPreferences.edit();
        editor.remove(strKey);
        editor.commit();
    }

    public static void clear(Context context) {
        SharedPreferences appPreferences = getAppPreferences(context);
        SharedPreferences.Editor editor = appPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void putInt(Context context, String strKey, int iValue) {
        SharedPreferences appPreferences = getAppPreferences(context);
        SharedPreferences.Editor editor = appPreferences.edit();
        editor.putInt(strKey, iValue);
        editor.commit();
    }

    public static void putLong(Context context, String strKey, long iValue) {
        SharedPreferences appPreferences = getAppPreferences(context);
        SharedPreferences.Editor editor = appPreferences.edit();
        editor.putLong(strKey, iValue);
        editor.commit();
    }

    public static void putBoolean(Context context, String strKey, boolean bFlag) {
        SharedPreferences appPreferences = getAppPreferences(context);
        SharedPreferences.Editor editor = appPreferences.edit();
        editor.putBoolean(strKey, bFlag);
        editor.commit();
    }

    public static void putString(Context context, String strKey, String strValue) {
        SharedPreferences appPreferences = getAppPreferences(context);
        SharedPreferences.Editor editor = appPreferences.edit();
        editor.putString(strKey, strValue);
        editor.commit();
    }

    public static int getInt(Context context, String strKey) {
        SharedPreferences appPreferences = getAppPreferences(context);
        return appPreferences.getInt(strKey, -99999);
    }

    public static long getLong(Context context, String strKey) {
        SharedPreferences appPreferences = getAppPreferences(context);
        return appPreferences.getLong(strKey, 0L);
    }

    public static boolean getBoolean(Context context, String strKey) {
        SharedPreferences appPreferences = getAppPreferences(context);
        return appPreferences.getBoolean(strKey, false);
    }

    public static String getString(Context context, String strKey) {
        SharedPreferences appPreferences = getAppPreferences(context);
        return appPreferences.getString(strKey, STRING_KEY_NOT_FOUND);
    }

    public static void setIsNewUser(Context context) {
        putBoolean(context, "newuser", true);
    }

    public static boolean isNewUser(Context context) {
        return getBoolean(context, "newuser");
    }
}
