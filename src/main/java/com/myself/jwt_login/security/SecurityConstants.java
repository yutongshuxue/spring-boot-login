package com.myself.jwt_login.security;

/**
 * ClassName: SecurityConstants
 * Description:
 *
 * @Author: lh
 * @Create: 2025/5/29 - 19:40
 * @Version: v1.0
 */
public class SecurityConstants {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION_TIME = 86400000; // 24小时
}
