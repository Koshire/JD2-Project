package com.itacademy.akulov.repository;

import com.itacademy.akulov.dto.PaginationDto;
import com.itacademy.akulov.entity.QUser;
import com.itacademy.akulov.entity.User;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import utils.PredicateBuilder;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private EntityManager entityManager;

    @Autowired
    public UserCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private JPAQuery<User> prepareQuery(PaginationDto paginationDto) {
        JPAQuery<User> jpaQuery = new JPAQuery<>(entityManager)
                .select(QUser.user)
                .from(QUser.user);
        List<Predicate> list = new ArrayList<>();
        if (paginationDto.getFio() != null) {
            list.add(QUser.user.userData.firstName
                    .concat(" ").concat(QUser.user.userData.middleName)
                    .concat(" ").concat(QUser.user.userData.lastName).contains(paginationDto.getFio()));
        }
        if (paginationDto.getBlockList() != null) {
            list.add(paginationDto.getBlockList() ? QUser.user.blockList.isTrue()
                    : QUser.user.blockList.isFalse());
        }
        if (paginationDto.getRole() != null) {
            list.add(QUser.user.role.eq(paginationDto.getRole()));
        }

        return jpaQuery.where(PredicateBuilder.build(list));
    }


    @Override
    public List<User> getAllByCustom(PaginationDto paginationDto) {
        Long offset = paginationDto.getPage() <= 1L ? 0L : paginationDto.getViewLimit() * (paginationDto.getPage() - 1);
        return prepareQuery(paginationDto).limit(paginationDto.getViewLimit()).offset(offset).fetch();
    }

    @Override
    public Long getAllByCustomCount(PaginationDto paginationDto) {
        return prepareQuery(paginationDto).fetchCount();
    }
}
