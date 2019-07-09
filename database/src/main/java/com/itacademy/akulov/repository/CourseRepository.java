package com.itacademy.akulov.repository;

import com.itacademy.akulov.entity.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends
        PagingAndSortingRepository<Course, Long>, CourseCustomRepository {

}
