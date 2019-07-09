package com.itacademy.akulov.repository;

import com.itacademy.akulov.dto.PaginationDto;
import com.itacademy.akulov.entity.Course;
import org.springframework.data.domain.Page;

public interface CourseCustomRepository {

    Page<Course> getPaginationRule(PaginationDto paginationDto);
}
