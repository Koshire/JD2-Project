package com.itacademy.akulov.dao;

import com.itacademy.akulov.entity.TeacherUser;

public class TeacherUserDao implements BaseDao<Long, TeacherUser> {

    private static final TeacherUserDao INSTANCE = new TeacherUserDao();


    public static TeacherUserDao getInstance() {
        return INSTANCE;
    }
}
