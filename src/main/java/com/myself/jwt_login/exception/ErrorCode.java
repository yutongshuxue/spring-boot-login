package com.myself.jwt_login.exception;

public enum ErrorCode {
    USERNAME_ALREADY_EXISTS("用户名已被使用"),
    EMAIL_ALREADY_REGISTERED("邮箱已被注册"),
    INVALID_CREDENTIALS("用户名或密码错误"),
    UNAUTHORIZED_ACCESS("未经授权的访问"),
    INTERNAL_SERVER_ERROR("服务器内部错误");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}