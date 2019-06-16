package com.itacademy.akulov.repository;

import com.itacademy.akulov.entity.Result;
import com.itacademy.akulov.entity.Result.UserCourse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends PagingAndSortingRepository<Result, Long> {

    Optional<Result> findByUserCourse(UserCourse userCourse);
    List<Result> findByCourseId(Long courseId);
    List<Result> findByUserResultId(Long userId);
}
