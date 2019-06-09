package com.itacademy.akulov.mapper;

import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.entity.User;

public class LoginMapper implements BaseMapper<User, LoginDto> {

    private static final LoginMapper INSTANCE = new LoginMapper();

    @Override
    public LoginDto mapToDto(User entity) {
        return LoginDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole().toString())
                .name(entity.getUserData().getFirstName())
                .middle(entity.getUserData().getMiddleName())
                .last(entity.getUserData().getLastName())
                .build();
    }

    @Override
    public User mapToEntity(LoginDto dto) {
        return new User()
                .setEmail(dto.getEmail())
                .setPassword(dto.getPassword())
                .setRole(Role.valueOf(dto.getRole()));
    }

    public static LoginMapper getInstance() {
        return INSTANCE;
    }
}
