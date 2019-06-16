package com.itacademy.akulov.repository;

import com.itacademy.akulov.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
