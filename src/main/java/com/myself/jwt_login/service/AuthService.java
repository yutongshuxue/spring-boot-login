package com.myself.jwt_login.service;

import com.myself.jwt_login.common.ResultCode;
import com.myself.jwt_login.dto.LoginRequest;
import com.myself.jwt_login.dto.RegisterRequest;
import com.myself.jwt_login.dto.UserInfo;
import com.myself.jwt_login.entity.Users;
import com.myself.jwt_login.mapper.UserMapper;
import com.myself.jwt_login.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthService(AuthenticationManager authenticationManager,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Transactional(readOnly = true)
    public String authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProvider.generateToken(authentication);
        } catch (BadCredentialsException e) {
            throw ResultCode.INVALID_CREDENTIALS.exception("用户名或密码错误");
        }
    }

    @Transactional
    public Long registerUser(RegisterRequest registerRequest) {
        if (userMapper.existsByUsername(registerRequest.getUsername())) {
            throw ResultCode.USER_EXISTS.exception("用户名已被使用");
        }

        if (userMapper.existsByEmail(registerRequest.getEmail())) {
            throw ResultCode.USER_EXISTS.exception("邮箱已被注册");
        }

        Users user = new Users();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setEnabled(true);

        userMapper.save(user);
        return user.getId();
    }

    @Transactional
    public String refreshToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw ResultCode.UNAUTHORIZED.exception("用户未登录");
        }

        return tokenProvider.generateToken(authentication);
    }

    public UserInfo getCurrentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw ResultCode.UNAUTHORIZED.exception("用户未登录");
        }

        return new UserInfo(authentication.getName(), "user@example.com");
    }
}