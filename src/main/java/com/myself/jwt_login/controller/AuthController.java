package com.myself.jwt_login.controller;

import com.myself.jwt_login.dto.ApiResponse;
import com.myself.jwt_login.dto.LoginRequest;
import com.myself.jwt_login.dto.RegisterRequest;
import com.myself.jwt_login.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(
            @Valid @RequestBody LoginRequest request) {
        String token = authService.authenticateUser(request);
        // 使用专用方法返回正确类型
        return ResponseEntity.ok(ApiResponse.success(token));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Long>> register(
            @Valid @RequestBody RegisterRequest request) {
        Long userId = authService.registerUser(request);
        // 使用专用方法返回正确类型
        return ResponseEntity.ok(ApiResponse.success(userId));
    }
}