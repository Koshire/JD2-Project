package com.itacademy.akulov.repository;

import com.itacademy.akulov.entity.Result;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends PagingAndSortingRepository<Result, Result.UserCourse> {

}
