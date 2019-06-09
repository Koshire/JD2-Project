package com.itacademy.akulov.dao;

import com.itacademy.akulov.entity.BaseEntity;
import lombok.Cleanup;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static com.itacademy.akulov.utils.SessionManager.getSession;

public interface BaseDao<T extends Serializable, E extends BaseEntity<T>> {

    default T save(E entity) {
        @Cleanup Session session = getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        return entity.getId();
    }

    default Optional<E> get(T id) {
        @Cleanup Session session = getSession();
        return Optional.ofNullable(session.get(getClazz(), id));
    }

    default void update(E entity) {
        @Cleanup Session session = getSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    default void delete(E entity) {
        @Cleanup Session session = getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    default List<E> getAll() {
        @Cleanup Session session = getSession();
        Class<E> clazz = getClazz();
        return session.createQuery(String.format("select e from %s e",
                clazz.getSimpleName()), clazz).list();
    }

    @SuppressWarnings("unchecked")
    default Class<E> getClazz() {
        Type entityType = ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[1];
        return (Class<E>) entityType;
    }
}
