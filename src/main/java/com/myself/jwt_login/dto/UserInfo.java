package com.myself.jwt_login.dto;

public class UserInfo {
    private String username;
    private String email;

    public UserInfo(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
