package com.itacademy.akulov.dao;

import com.itacademy.akulov.entity.Course;
import com.itacademy.akulov.entity.Result;
import com.itacademy.akulov.entity.User;
import lombok.Cleanup;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static com.itacademy.akulov.entity.Result.UserCourse;
import static com.itacademy.akulov.utils.SessionManager.getSession;

public class ResultDao {

    private static final ResultDao INSTANCE = new ResultDao();


    public UserCourse save(Result entity) {
        @Cleanup Session session = getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        return entity.getUserCourse();
    }

    public Optional<Result> get(UserCourse userCourse) {
        @Cleanup Session session = getSession();
        return Optional.ofNullable(session.get(Result.class, userCourse));
    }

    public void update(Result entity) {
        @Cleanup Session session = getSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    public void delete(Result entity) {
        @Cleanup Session session = getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public List<Result> getAll() {
        @Cleanup Session session = getSession();
        return session.createQuery("select r from Result r", Result.class).list();
    }

    public List<Result> getAllByUser(User user) {
        @Cleanup Session session = getSession();
        return session.createQuery
                ("select r from Result r where Result.userResult = :user", Result.class)
                .setParameter("user", user).list();
    }

    public List<Result> getAllByCourse(Course course) {
        @Cleanup Session session = getSession();
        return session.createQuery
                ("select r from Result r where Result.userResult = :course", Result.class)
                .setParameter("course", course).list();
    }

    public static ResultDao getInstance() {
        return INSTANCE;
    }
}
