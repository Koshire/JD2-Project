package com.itacademy.akulov.mapper;

import com.itacademy.akulov.dto.ResultDto;
import com.itacademy.akulov.entity.Result;

public class ResultMapper implements BaseMapper<Result, ResultDto> {

    private static final ResultMapper INSTANCE = new ResultMapper();

    private CourseMapper courseMapper = CourseMapper.INSTANCE;
    private LoginMapper loginMapper = LoginMapper.getInstance();

    @Override
    public ResultDto mapToDto(Result entity) {
        return ResultDto.builder()
                .course(courseMapper.courseToCourseCDto(entity.getCourse()))
                .userResult(loginMapper.mapToDto(entity.getUserResult()))
                .result(entity.getResult())
                .build();
    }

    @Override
    public Result mapToEntity(ResultDto dto) {
        return Result.builder()
                .userCourse(Result.UserCourse.builder()
                        .courseId(dto.getCourse().getId())
                        .userId(dto.getUserResult().getId())
                        .build())
                .userResult(loginMapper.mapToEntity(dto.getUserResult()))
                .course(courseMapper.courseDtoCToCourse(dto.getCourse()))
                .result(dto.getResult())
                .build();
    }

    public static ResultMapper getInstance() {
        return INSTANCE;
    }
}
