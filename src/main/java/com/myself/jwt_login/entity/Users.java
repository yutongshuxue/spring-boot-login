package com.myself.jwt_login.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Long id;
    private String username;
    private String password;
    private String email;
    @Builder.Default
    private Boolean enabled = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}