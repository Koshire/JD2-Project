package com.itacademy.akulov.mapper;

import com.itacademy.akulov.dto.CourseCommentsDto;
import com.itacademy.akulov.entity.CourseComments;

public class CourseCommentsMapper {

    private CourseMapper courseMapper = CourseMapper.INSTANCE;
    private LoginMapper loginMapper = LoginMapper.getInstance();
    private static final CourseCommentsMapper INSTANCE = new CourseCommentsMapper();

    public CourseComments dtoToEntity(CourseCommentsDto courseCommentsDto) {
        return CourseComments.builder()
                .id(courseCommentsDto.getId())
                .user(loginMapper.mapToEntity(courseCommentsDto.getUser()))
                .comment(courseCommentsDto.getComment())
                .course(courseMapper.courseDtoCToCourse(courseCommentsDto.getCourse()))
                .build();
    }

    public CourseCommentsDto entityToDto(CourseComments courseComments) {
        return CourseCommentsDto.builder()
                .id(courseComments.getId())
                .comment(courseComments.getComment())
                .user(loginMapper.mapToDto(courseComments.getUser()))
                .course(courseMapper.courseToCourseCDto(courseComments.getCourse()))
                .build();
    }

    public static CourseCommentsMapper getInstance() {
        return INSTANCE;
    }
}
