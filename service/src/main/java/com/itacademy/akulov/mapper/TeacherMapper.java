package com.itacademy.akulov.mapper;

import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.dto.TeacherDto;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.entity.TeacherUser;
import com.itacademy.akulov.entity.UserData;

import java.util.stream.Collectors;


public class TeacherMapper implements BaseMapper<TeacherUser, TeacherDto> {

    private static final TeacherMapper INSTANCE = new TeacherMapper();

    private CourseMapper courseMapper = CourseMapper.INSTANCE;

    public TeacherDto mapToDto(LoginDto entity) {
        return TeacherDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .blockList(entity.isBlockList())
                .role(entity.getRole())
                .last(entity.getLast())
                .middle(entity.getMiddle())
                .name(entity.getName())
                .phone(entity.getPhone())
                .build();
    }

    @Override
    public TeacherDto mapToDto(TeacherUser entity) {
        TeacherDto teacherDto = TeacherDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .blockList(entity.getBlockList())
                .role(entity.getRole().getName())
                .last(entity.getUserData().getLastName())
                .middle(entity.getUserData().getMiddleName())
                .name(entity.getUserData().getFirstName())
                .phone(entity.getUserData().getPhone())
                .build();
        teacherDto.setCourse(entity.getCourses().stream().map(courseMapper::courseToCourseCDto)
                .collect(Collectors.toList()));
        return teacherDto;
    }

    @Override
    public TeacherUser mapToEntity(TeacherDto dto) {
        return TeacherUser.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.valueOf(dto.getRole()))
                .userData(UserData.builder()
                        .phone(dto.getPhone())
                        .firstName(dto.getName())
                        .middleName(dto.getMiddle())
                        .lastName(dto.getLast())
                        .build())
                .blockList(dto.isBlockList())
                .build();
    }

    public TeacherUser mapToEntity(LoginDto dto) {
        return TeacherUser.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.valueOf(dto.getRole()))
                .userData(UserData.builder()
                        .phone(dto.getPhone())
                        .firstName(dto.getName())
                        .middleName(dto.getMiddle())
                        .lastName(dto.getLast())
                        .build())
                .blockList(dto.isBlockList())
                .build();
    }

    public static TeacherMapper getInstance() {
        return INSTANCE;
    }
}
