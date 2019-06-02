package com.itacademy.akulov.dao;

import com.itacademy.akulov.entity.KBComments;

public class KBCommentsDao implements BaseDao<Long, KBComments> {

    private static final KBCommentsDao INSTANCE = new KBCommentsDao();


    public static KBCommentsDao getInstance() {
        return INSTANCE;
    }
}
