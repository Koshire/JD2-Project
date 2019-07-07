package com.itacademy.akulov.repository;

import com.itacademy.akulov.dto.PaginationDto;
import com.itacademy.akulov.entity.Course;
import com.itacademy.akulov.entity.CourseType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.itacademy.akulov.entity.QCourse.course;

public class CourseCustomRepositoryImpl implements CourseCustomRepository {

    private final EntityManager em;

    @Autowired
    public CourseCustomRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Page<Course> getPaginationRule(PaginationDto paginationDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!paginationDto.getParameter1().equals("")) {
            booleanBuilder.and(course.name.contains(paginationDto.getParameter1()));
        }
        if (!paginationDto.getParameter2().equals("")) {
            booleanBuilder.and(course.type.eq(CourseType.valueOf(paginationDto.getParameter2())));
        }
        if (paginationDto.getParameter3().equals("")) {
            paginationDto.setParameter3("1970-01-01");
        }
        if (paginationDto.getParameter4().equals("")) {
            paginationDto.setParameter4("2200-01-01");
        }
        LocalDate from = LocalDate.parse(paginationDto.getParameter3());
        LocalDate to = LocalDate.parse(paginationDto.getParameter4());
        booleanBuilder.and(course.startDate.between(from, to));

        Long totalElements = new JPAQuery<Course>(em)
                .select(course)
                .from(course)
                .where(booleanBuilder.getValue())
                .fetchCount();

        List<Course> list = new JPAQuery<Course>(em)
                .select(course)
                .from(course)
                .where(booleanBuilder.getValue())
                .limit(paginationDto.getPageSize())
                .offset(paginationDto.getPageSize() * paginationDto.getPage())
                .fetch();

        return new PageImpl<>(list, PageRequest.of(paginationDto.getPage(),
                paginationDto.getPageSize()), totalElements);
    }
}
