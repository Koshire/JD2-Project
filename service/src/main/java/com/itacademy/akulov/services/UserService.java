package com.itacademy.akulov.services;

import com.itacademy.akulov.dao.UserDao;
import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.entity.User;
import com.itacademy.akulov.mapper.LoginMapper;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final LoginMapper loginMapper = LoginMapper.getInstance();
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public Long getSizeByCustom(FindDto findDto) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Long size = userDao.size(userDao.findByQueue(session, findDto));

        session.getTransaction().commit();
        return size;
    }

    public List<LoginDto> getByCustomLO(FindDto findDto) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> list = new ArrayList<>(userDao.getByQueryLO(userDao.findByQueue(session, findDto),
                findDto.getLimit(), findDto.getOffset()));
        List<LoginDto> result = list.stream().map(loginMapper::mapToDto)
                .collect(Collectors.toList());
        session.getTransaction().commit();
        return result;
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
