package com.itacademy.akulov.dao;

import com.itacademy.akulov.entity.CourseComments;

public class CourseCommentsDao implements BaseDao<Long, CourseComments> {

    private static final CourseCommentsDao INSTANCE = new CourseCommentsDao();


    public static CourseCommentsDao getInstance() {
        return INSTANCE;
    }
}
