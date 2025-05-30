package com.myself.jwt_login.mapper;

import com.myself.jwt_login.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<Users> findByUsername(@Param("username") String username);
    void save(Users user);
    boolean existsByUsername(@Param("username") String username);
    boolean existsByEmail(@Param("email") String email);
}
