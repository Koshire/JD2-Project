package com.itacademy.akulov.repository;

import com.itacademy.akulov.entity.CourseTeacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTeacherRepository extends PagingAndSortingRepository<CourseTeacher, CourseTeacher.UserCourse> {

}
