package com.example.wood121.viewdemos.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wood121 on 2017/12/20.
 * 左边三个tab页面的fragment封装
 */

public abstract class BaseFragment extends Fragment {

    protected FragmentActivity mActivity; //这里可以返回BaseActivity对象
    protected Context mContext;
    private boolean mIsPrepare;
    private View mRootView;
    private boolean mIsVisible;
    private Unbinder unbinder;

    //这是必须需要的
    public BaseFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context.getApplicationContext();
        this.mActivity = (FragmentActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(setLayoutResouceId(), container, false);
            unbinder = ButterKnife.bind(this, mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData(getArguments());
        setListener();

        mIsPrepare = true;  //判定是否需要懒加载
        onLazyLoad();
    }

    private void onLazyLoad() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser) {
            onVisibleToUser();
        }
    }

    //可见的情况下才进行数据的加载
    private void onVisibleToUser() {
        if (mIsPrepare && mIsVisible) {
            onLazyLoad();
        }
    }

    //封装控件的查找
    protected <T extends View> T findViewById(int id) {
        if (mRootView == null) {
            return null;
        }

        return (T) mRootView.findViewById(id);
    }

    //设置布局资源id
    protected abstract int setLayoutResouceId();

    //获取数据
    protected abstract void initData(Bundle arguments);

    //设置监听
    protected abstract void setListener();

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
