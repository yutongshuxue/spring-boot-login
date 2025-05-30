package com.myself.jwt_login.security;

import com.myself.jwt_login.entity.Users;
import com.myself.jwt_login.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userMapper.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }
        return new UserDetailsImpl(user.get());
    }
}
