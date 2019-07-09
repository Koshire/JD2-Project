package com.itacademy.akulov.mapper;

import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.dto.StudentDto;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.entity.StudentUser;
import com.itacademy.akulov.entity.UserData;

import java.util.stream.Collectors;


public class StudentMapper implements BaseMapper<StudentUser, StudentDto> {

    private static final StudentMapper INSTANCE = new StudentMapper();
    private ResultMapper resultMapper = ResultMapper.getInstance();

    public StudentDto mapToDto(LoginDto entity) {
        return StudentDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .middle(entity.getMiddle())
                .name(entity.getName())
                .last(entity.getLast())
                .phone(entity.getPhone())
                .blockList(entity.isBlockList())
                .build();
    }

    @Override
    public StudentDto mapToDto(StudentUser entity) {
        return StudentDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole().getName())
                .middle(entity.getUserData().getMiddleName())
                .name(entity.getUserData().getFirstName())
                .last(entity.getUserData().getLastName())
                .phone(entity.getUserData().getPhone())
                .blockList(entity.getBlockList())
                .results(entity.getResult().stream().map(resultMapper::mapToDto).collect(Collectors.toList()))
                .build();
    }

    @Override
    public StudentUser mapToEntity(StudentDto dto) {
        return StudentUser.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.valueOf(dto.getRole()))
                .userData(UserData.builder()
                        .lastName(dto.getLast())
                        .middleName(dto.getMiddle())
                        .firstName(dto.getName())
                        .phone(dto.getName())
                        .build())
                .blockList(dto.isBlockList())
                .build();
    }

    public StudentUser mapToEntity(LoginDto dto) {
        return StudentUser.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.valueOf(dto.getRole()))
                .userData(UserData.builder()
                        .lastName(dto.getLast())
                        .middleName(dto.getMiddle())
                        .firstName(dto.getName())
                        .phone(dto.getPhone())
                        .build())
                .blockList(dto.isBlockList())
                .build();
    }

    public static StudentMapper getInstance() {
        return INSTANCE;
    }
}
