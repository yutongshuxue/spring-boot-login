package com.myself.jwt_login.controller;

import com.myself.jwt_login.common.ApiResult;
import com.myself.jwt_login.dto.LoginRequest;
import com.myself.jwt_login.dto.RegisterRequest;
import com.myself.jwt_login.dto.UserInfo;
import com.myself.jwt_login.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResult<String> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.authenticateUser(request);
        return ApiResult.success("登录成功", token);
    }

    @PostMapping("/register")
    public ApiResult<Long> register(@Valid @RequestBody RegisterRequest request) {
        Long userId = authService.registerUser(request);
        return ApiResult.success("用户注册成功", userId);
    }

    @PostMapping("/refresh-token")
    public ApiResult<String> refreshToken() {
        String newToken = authService.refreshToken();
        return ApiResult.success("令牌刷新成功", newToken);
    }

    @GetMapping("/me")
    public ApiResult<UserInfo> getCurrentUserInfo() {
        UserInfo userInfo = authService.getCurrentUserInfo();
        return ApiResult.success(userInfo);
    }
}