package com.myself.jwt_login.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {
    private int code;
    private String msg;
    private T data;

    public ApiResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(ResultCode.SUCCESS.getCode(), "操作成功", data);
    }

    public static <T> ApiResult<T> success(String msg, T data) {
        return new ApiResult<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static ApiResult<Void> success() {
        return new ApiResult<>(ResultCode.SUCCESS.getCode(), "操作成功", null);
    }

    public static <T> ApiResult<T> error(int code, String msg) {
        return new ApiResult<>(code, msg, null);
    }

    public static <T> ApiResult<T> error(ResultCode resultCode) {
        return new ApiResult<>(resultCode.getCode(), resultCode.getMsg(), null);
    }

    public static <T> ApiResult<T> error(ResultCode resultCode, String customMsg) {
        return new ApiResult<>(resultCode.getCode(), customMsg, null);
    }

    // Getters
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}