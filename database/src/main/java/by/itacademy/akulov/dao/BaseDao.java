package by.itacademy.akulov.dao;

import org.hibernate.Session;

import java.util.List;

public abstract class BaseDao<T> {

    abstract List<T> findAll(Session session);

    abstract T findOne(Session session, String value);

    abstract T save(Session session, T value);

    abstract T update(Session session, T value);

    abstract void delete(Session session, T value);
}
