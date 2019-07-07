package com.itacademy.akulov.repository;

import com.itacademy.akulov.entity.Result;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends PagingAndSortingRepository<Result, Result.UserCourse> {

    List<Result> findByCourseId(Long id);
}
