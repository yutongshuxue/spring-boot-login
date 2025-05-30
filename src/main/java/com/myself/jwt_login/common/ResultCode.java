package com.myself.jwt_login.common;

public enum ResultCode {
    // 成功状态码
    SUCCESS(200, "操作成功"),

    // 客户端错误
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),

    // 服务器错误
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    // 认证授权
    INVALID_CREDENTIALS(1001, "用户名或密码错误"),
    TOKEN_EXPIRED(1002, "令牌已过期"),
    TOKEN_INVALID(1003, "令牌无效"),

    // 用户相关
    USER_NOT_EXIST(1101, "用户不存在"),
    USER_EXISTS(1102, "用户已存在"),

    // 通用业务错误
    OPERATION_FAILED(9999, "操作失败");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ApiException exception() {
        return new ApiException(this);
    }

    public ApiException exception(String message) {
        return new ApiException(this, message);
    }
}