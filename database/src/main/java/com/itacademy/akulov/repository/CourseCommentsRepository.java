package com.itacademy.akulov.repository;

import com.itacademy.akulov.entity.CourseComments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseCommentsRepository extends PagingAndSortingRepository<CourseComments, Long> {

    Page<CourseComments> findAllByCourseId(Long id, Pageable pageable);
    List<CourseComments> findAllByUserId(Long userId);
    boolean deleteByUserIdAndCourseId(Long userId, Long courseId);
    void deleteById(Long commentId);
}
