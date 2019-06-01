package by.itacademy.akulov.dao;

import by.itacademy.akulov.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

import static by.itacademy.akulov.entity.QUser.user;

public class UserDao extends BaseDao<User> {

    private static final UserDao INSTANCE = new UserDao();

    @Override
    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .fetch();
    }

    @Override
    public User findOne(Session session, String email) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.email.eq(email))
                .fetchOne();
    }

    @Override
    public User save(Session session, User value) {
        session.save(value);
        return value;
    }

    @Override
    public User update(Session session, User value) {
        session.update(value);
        return value;
    }

    @Override
    public void delete(Session session, User value) {
        session.delete(value);
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
