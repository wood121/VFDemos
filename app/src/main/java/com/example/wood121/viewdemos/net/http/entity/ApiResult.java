package com.example.wood121.viewdemos.net.http.entity;

/**
 * @author wood121
 * @desc
 * @time:2018/8/12
 */
public class ApiResult<T> {
    private static int SUCCESS_CODE=0;//成功的code


    public boolean isSuccess(){
        return getErrorCode() == SUCCESS_CODE;
    }

    /**
     * 0：成功，1：失败
     */
    private int errorCode;

    private String errorMsg;

    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
