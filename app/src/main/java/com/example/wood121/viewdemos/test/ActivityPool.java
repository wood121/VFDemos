package com.example.wood121.viewdemos.test;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by wood121 on 2018/7/19.
 */

public class ActivityPool {

    public static ActivityPool activityPool = new ActivityPool();
    public static ArrayList<Activity> acts = new ArrayList<>();

    public static ActivityPool getActivityPool() {
        return activityPool;
    }

    public static void addActivity(Activity activity) {
        acts.add(activity);
    }
}
