package com.myself.jwt_login.dto;
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    // 正确的泛型构造函数
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // 针对 String 类型的专用方法
    public static ApiResponse<String> success(String data) {
        return new ApiResponse<>(true, "操作成功", data);
    }

    // 针对 Long 类型的专用方法
    public static ApiResponse<Long> success(Long data) {
        return new ApiResponse<>(true, "操作成功", data);
    }

    // 错误响应
    public static ApiResponse<Void> error(String message) {
        return new ApiResponse<>(false, message, null);
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}