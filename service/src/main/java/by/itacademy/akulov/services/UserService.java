package by.itacademy.akulov.services;

import by.itacademy.akulov.dao.UserDao;
import by.itacademy.akulov.dto.LoginDto;
import by.itacademy.akulov.mapper.UserMapper;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<LoginDto> getAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<LoginDto> dtoList = userDao.findAll(session).stream().map(userMapper::mapToDto).collect(Collectors.toList());
        session.getTransaction().commit();
        return dtoList;
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
