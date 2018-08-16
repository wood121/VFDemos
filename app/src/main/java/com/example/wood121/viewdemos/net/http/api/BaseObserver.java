package com.example.wood121.viewdemos.net.http.api;

import android.accounts.NetworkErrorException;
import android.app.ProgressDialog;
import android.content.Context;

import com.example.wood121.viewdemos.net.http.entity.ApiResult;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author wood121
 * @desc
 * @time:2018/8/12
 */
public abstract class BaseObserver<T> implements Observer<ApiResult<T>> {

    private Context mContext;

    public BaseObserver(Context context) {
        this.mContext = context;
    }

    public BaseObserver() {
    }


    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(ApiResult<T> tApiResult) {
        onRequestEnd();
        if (tApiResult.isSuccess()) {
            try {
                onSuccess(tApiResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(tApiResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        if (e instanceof ConnectException
                || e instanceof TimeoutException
                || e instanceof NetworkErrorException
                || e instanceof UnknownHostException) {
            try {
                onFailure(e, true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            try {
                onFailure(e, false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(ApiResult<T> tApiResult) throws Exception;

    protected abstract void onFailure(Throwable e, boolean isNetworkError) throws Exception;

    /**
     * 返回成功了，但是code错误
     *
     * @param tApiResult
     * @throws Exception
     */
    private void onCodeError(ApiResult<T> tApiResult) throws Exception {

    }

    protected void onRequestStart() {

    }

    public void showProgressDialog() {
        ProgressDialog.show(mContext, "wood121", "请稍后");
    }

    private void onRequestEnd() {
        closeProgressDialog();
    }

    public void closeProgressDialog() {
//        ProgressDialog;
    }

}
