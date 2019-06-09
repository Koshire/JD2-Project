package com.itacademy.akulov.dao;

import com.itacademy.akulov.entity.KnowlegeBase;

public class KnowlegeBaseDao implements BaseDao<Long, KnowlegeBase> {

    private static final KnowlegeBaseDao INSTANCE = new KnowlegeBaseDao();


    public static KnowlegeBaseDao getInstance() {
        return INSTANCE;
    }
}
