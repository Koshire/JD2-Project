package com.itacademy.akulov.dao;

import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.entity.QUser;
import com.itacademy.akulov.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

public class UserDao implements BaseDao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();

    public JPAQuery<User> findByQueue(Session session, FindDto findDto) {

        JPAQuery<User> jpaQuery = new JPAQuery<User>(session)
                .select(QUser.user)
                .from(QUser.user);

        if (findDto.getRole() != null) {
            jpaQuery.where(QUser.user.role.eq(findDto.getRole()));
        }

        jpaQuery.where((QUser.user.userData.firstName
                .concat(" ").concat(QUser.user.userData.middleName)
                .concat(" ").concat(QUser.user.userData.lastName)).contains(findDto.getFio()));

        if (findDto.getBlockList()) {
            jpaQuery.where(QUser.user.blockList.isTrue());
        } else {
            jpaQuery.where(QUser.user.blockList.isFalse());
        }

        return jpaQuery;
    }

    public Long size(JPAQuery<User> jpaQuery) {
        return jpaQuery.fetchCount();
    }

    public List<User> getByQueryLO(JPAQuery<User> jpaQuery, Long limit, Long offset) {
        return jpaQuery.limit(limit).offset(offset).fetch();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
