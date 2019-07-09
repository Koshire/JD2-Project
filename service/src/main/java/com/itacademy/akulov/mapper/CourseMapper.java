package com.itacademy.akulov.mapper;

import com.itacademy.akulov.dto.CourseDto;
import com.itacademy.akulov.dto.CourseDtoC;
import com.itacademy.akulov.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "name", target = "name")
    CourseDto courseToCourseDto(Course course);

    Course courseDtoToCourse(CourseDto courseDto);

    CourseDtoC courseToCourseCDto(Course course);

    Course courseDtoCToCourse(CourseDtoC courseDto);
}
