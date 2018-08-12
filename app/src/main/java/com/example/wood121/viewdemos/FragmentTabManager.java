package com.example.wood121.viewdemos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.wood121.viewdemos.fragment.APIViewFragment;
import com.example.wood121.viewdemos.fragment.FrameSDKFragment;
import com.example.wood121.viewdemos.fragment.MathJniFragment;
import com.example.wood121.viewdemos.fragment.MineFragment;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wood121
 * @desc
 * @time:2018/8/6
 */
public class FragmentTabManager {
    public static final String API_VIEW_FRAGMENT = "APIViewFragment";
    public static final String FRAME_SDK_FRAGMENT = "FrameSDKFragment";
    public static final String MATH_JNI_FRAGMENT = "MathJniFragment";
    public static final String MINE_FRAGMENT = "MineFragment";
    private static FragmentTabManager sFragmentTabManager;
    private static WeakReference<Context> mContextWeakReference;
    private ConcurrentHashMap<String, Fragment> mFragmentsMap;

    private FragmentTabManager() {
    }

    public static FragmentTabManager getInstance(Context context) {
        if (sFragmentTabManager == null) {
            synchronized (FragmentTabManager.class) {
                if (sFragmentTabManager == null) {
                    sFragmentTabManager = new FragmentTabManager();
                    mContextWeakReference = new WeakReference<>(context);
                }
            }
        }
        return sFragmentTabManager;
    }

    public void initFragments() {
        if (mFragmentsMap == null) {
            mFragmentsMap = new ConcurrentHashMap<>();
        }
        MainActivity mainActivity = (MainActivity) mContextWeakReference.get();
        mFragmentsMap.clear();

        mFragmentsMap.put(API_VIEW_FRAGMENT, APIViewFragment.newInstance());
        mFragmentsMap.put(FRAME_SDK_FRAGMENT, FrameSDKFragment.newInstance());
        mFragmentsMap.put(MATH_JNI_FRAGMENT, MathJniFragment.newInstance());
        mFragmentsMap.put(MINE_FRAGMENT, MineFragment.newInstance());
    }

    public void slectFragment(String fragmentTag) {
        MainActivity mainActivity = (MainActivity) mContextWeakReference.get();
        FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
        for (String tag :
                mFragmentsMap.keySet()) {
            Fragment fragment = mFragmentsMap.get(tag);
            if (fragment != null)
                if (fragmentTag.equals(tag)) {
                    if (fragment.isAdded()) {
                        fragmentTransaction.show(fragment);
                    } else {
                        fragmentTransaction.add(R.id.fragment_container, fragment, fragment.getClass().getName());
                    }
                } else {
                    if (fragment.isAdded()) {
                        fragmentTransaction.hide(fragment);
                    }
                }
        }
        fragmentTransaction.commit();
    }

    public Fragment getFragment(String fragmentTag) {
        Fragment fragment = null;
        for (String key :
                mFragmentsMap.keySet()) {
            if (fragmentTag.equals(key)) {
                fragment = mFragmentsMap.get(key);
            }
        }
        return fragment;
    }

    public void onDestroy() {
        if (mContextWeakReference != null) {
            mContextWeakReference.clear();
        }
        if (mFragmentsMap != null) {
            mFragmentsMap.clear();
        }
    }
}
