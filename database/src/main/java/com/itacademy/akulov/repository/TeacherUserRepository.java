package com.itacademy.akulov.repository;

import com.itacademy.akulov.entity.TeacherUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherUserRepository extends PagingAndSortingRepository<TeacherUser, Long> {

}
