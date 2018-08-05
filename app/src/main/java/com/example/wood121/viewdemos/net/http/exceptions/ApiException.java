package com.example.wood121.viewdemos.net.http.exceptions;

/**
 * Created by wood121 on 2018/7/17.
 */

public class ApiException extends RuntimeException {

    public static final int USER_NOT_EXIST = 100;
    public static final int WRONG_PASSWORD = 101;
    private static String message;

    public ApiException(int resultCode) {
        this(getApiExcetionMsg(resultCode));
    }

    public ApiException(String detailMsg) {
        super(detailMsg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    private static String getApiExcetionMsg(int code) {
        switch (code) {
            case USER_NOT_EXIST:
                message = "该用户不存在";
                break;
            case WRONG_PASSWORD:
                message = "密码错误";
                break;
            default:
                message = "未知错误";
        }
        return message;

    }
}
