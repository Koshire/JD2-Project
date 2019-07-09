package com.itacademy.akulov.repository;

import com.itacademy.akulov.entity.KnowlegeBase;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowlegeBaseRepository extends PagingAndSortingRepository<KnowlegeBase, Long> {

    void deleteById(Long id);
}

