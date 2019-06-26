package com.example.wood121.viewdemos.frames.net.HttpUrlConnection.urlhttplib;


import android.os.Handler;
import android.os.Looper;

/**
 * @author: liuzhengling
 * @date: 2019/6/13
 * @version:
 * @des: 成功（有数据、空数据）、失败回调
 */
abstract class HttpCallBack<T> {
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    /**
     * 成功的回调
     *
     * @param woodResponse
     */
    public void onSuccess(WoodResponse woodResponse) {
        T obj = onParseResponse(woodResponse);
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                onResponse(obj);
            }
        });
    }

    protected abstract T onParseResponse(WoodResponse woodResponse);

    protected abstract void onResponse(T woodResponse);

    /**
     * 错误的回调
     *
     * @param woodResponse
     */
    public void onError(WoodResponse woodResponse) {
        final String errorMessage;
        if (woodResponse.inputStream != null) {
            errorMessage = StreamUtil.getStreamString(woodResponse.inputStream);
        } else if (woodResponse.errorStream != null) {
            errorMessage = StreamUtil.getStreamString(woodResponse.errorStream);
        } else if (woodResponse.exception != null) {
            errorMessage = woodResponse.exception.getMessage();
        } else {
            errorMessage = "";
        }
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                onFailure(woodResponse.code, errorMessage);
            }
        });
    }

    protected abstract void onFailure(int code, String errorMessage);
}
