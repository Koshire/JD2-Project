package com.itacademy.akulov.mapper;

import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.entity.StudentUser;
import com.itacademy.akulov.entity.TeacherUser;
import com.itacademy.akulov.entity.User;
import com.itacademy.akulov.entity.UserData;

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
                .phone(entity.getUserData().getPhone())
                .blockList(entity.getBlockList())
                .build();
    }

    public LoginDto mapToStudentDto(StudentUser entity) {
        return mapToDto(entity);
    }

    public LoginDto mapToTeacherDto(TeacherUser entity) {
        return mapToDto(entity);
    }

    @Override
    public User mapToEntity(LoginDto dto) {
        return new User()
                .setId(dto.getId())
                .setEmail(dto.getEmail())
                .setPassword(dto.getPassword())
                .setRole(Role.valueOf(dto.getRole()))
                .setBlockList(dto.isBlockList())
                .setUserData(UserData.builder()
                        .lastName(dto.getLast())
                        .middleName(dto.getMiddle())
                        .firstName(dto.getName())
                        .phone(dto.getPhone())
                        .build());
    }

    public StudentUser mapToStudentEntity(LoginDto dto) {
        return StudentUser.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.valueOf(dto.getRole()))
                .userData(UserData.builder()
                        .lastName(dto.getLast())
                        .middleName(dto.getMiddle())
                        .firstName(dto.getName())
                        .phone(dto.getPhone())
                        .build())
                .blockList(false)
                .build();
    }

    public TeacherUser mapToTeacherEntity(LoginDto dto) {
        return TeacherUser.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.valueOf(dto.getRole()))
                .userData(UserData.builder()
                        .lastName(dto.getLast())
                        .middleName(dto.getMiddle())
                        .firstName(dto.getName())
                        .phone(dto.getPhone())
                        .build())
                .blockList(false)
                .build();
    }

    public static LoginMapper getInstance() {
        return INSTANCE;
    }
}
