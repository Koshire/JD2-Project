package com.itacademy.akulov.repository;

import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.entity.QUser;
import com.itacademy.akulov.entity.User;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.PredicateBuilder;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private EntityManager entityManager;

    @Autowired
    public UserCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private JPAQuery<User> prepareQuery(FindDto findDto) {

        JPAQuery<User> jpaQuery = new JPAQuery<>(entityManager)
                .select(QUser.user)
                .from(QUser.user);

        List<Predicate> list = new ArrayList<>();
        if (findDto.getFio() != null) {
            list.add(QUser.user.userData.firstName
                    .concat(" ").concat(QUser.user.userData.middleName)
                    .concat(" ").concat(QUser.user.userData.lastName).contains(findDto.getFio()));
        }
        if (findDto.getBlockList() != null) {
            list.add(findDto.getBlockList() ? QUser.user.blockList.isTrue()
                    : QUser.user.blockList.isFalse());
        }
        if (findDto.getRole() != null) {
            list.add(QUser.user.role.eq(findDto.getRole()));
        }

        return jpaQuery.where(PredicateBuilder.build(list));
    }


    @Override
    public List<User> getAllByCustom(FindDto findDto) {
        Long offset = findDto.getPage() <= 1L ? 0L : findDto.getViewLimit() * (findDto.getPage()-1);
        return prepareQuery(findDto).limit(findDto.getViewLimit()).offset(offset).fetch();
    }

    @Override
    public Long getAllByCustomCount(FindDto findDto) {
        return prepareQuery(findDto).fetchCount();
    }
}
