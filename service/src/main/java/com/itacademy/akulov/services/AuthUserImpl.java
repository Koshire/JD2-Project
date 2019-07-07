package com.itacademy.akulov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthUserImpl implements AuthUser {


    private UserService userService;

    @Autowired
    public AuthUserImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return Optional.of(name)
                .map(userService::getLogin)
                .map(user -> User.builder()
                        .username(user.get().getEmail())
                        .password(user.get().getPassword())
                        .authorities(user.get().getRole())
                        .accountLocked(user.get().isBlockList())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
