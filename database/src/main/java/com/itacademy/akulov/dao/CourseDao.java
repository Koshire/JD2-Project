package com.itacademy.akulov.dao;

import com.itacademy.akulov.entity.Course;

public class CourseDao implements BaseDao<Long, Course> {

    private static final CourseDao INSTANCE = new CourseDao();


    public static CourseDao getInstance() {
        return INSTANCE;
    }
}
