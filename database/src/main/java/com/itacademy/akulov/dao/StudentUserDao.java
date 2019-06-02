package com.itacademy.akulov.dao;

import com.itacademy.akulov.entity.StudentUser;

public class StudentUserDao implements BaseDao<Long, StudentUser> {

    private static final StudentUserDao INSTANCE = new StudentUserDao();


    public static StudentUserDao getInstance() {
        return INSTANCE;
    }
}
