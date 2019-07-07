package com.itacademy.akulov.repository;

import com.itacademy.akulov.entity.StudentUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentUserRepository extends PagingAndSortingRepository<StudentUser, Long> {

}
