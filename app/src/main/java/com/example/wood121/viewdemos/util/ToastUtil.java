package com.example.wood121.viewdemos.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wood121 on 2017/12/22.
 * 单例Toast，只弹一次
 */

public class ToastUtil {
    private static Toast mToast;

    public static void showToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
