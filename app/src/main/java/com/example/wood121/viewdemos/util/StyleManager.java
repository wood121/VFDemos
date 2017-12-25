package com.example.wood121.viewdemos.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by wood121 on 2017/12/21.
 * app样式设置：statusBar,
 */

public class StyleManager {

    private static StyleManager mInstance;
    private static Context mContext;

    /**
     * 获取StyleManager实例
     *
     * @return StyleManager
     */
    public static StyleManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (StyleManager.class) {
                if (mInstance == null) {
                    mInstance = new StyleManager();
                    mContext = context;
                }
            }
        }
        return mInstance;
    }

    public String getClassName(Object o) {
        Class<? extends Object> c = o.getClass();
        String cName = c.getName();
        String[] tmp = cName.split("\\.");
        cName = tmp[tmp.length - 1];
        return cName;
    }

    /**
     * 设置半透明状态
     *
     * @param on
     * @param activity
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on, Activity activity) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void setStatusBarStyle(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //针对引导界面全屏特殊处理
            String activityName = getClassName(activity);
            if ("IntroductionActivity".equals(activityName)) {
                return;
            }
            //进行界面设置
            View contentView =
                    activity.findViewById(android.R.id.content);
            if (contentView != null) {
                ViewGroup viewGroup = (ViewGroup) contentView;
                View childView = viewGroup.getChildAt(0);
                if (childView != null) {
                    childView.setFitsSystemWindows(true);
                    setTranslucentStatus(true, activity);

                    SystemBarTintManagerUtils tintManager = new SystemBarTintManagerUtils(activity);
                    tintManager.setStatusBarTintEnabled(true);
                    tintManager.setStatusBarTintColor(color);
                }
            }
        }
    }
}
