package com.itacademy.akulov.entity;

import com.itacademy.akulov.utils.ItemsCreator;
import org.hamcrest.CoreMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TestMeth {

    private static ItemsCreator itemsCreator = ItemsCreator.getInstance();
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void fillItems() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        itemsCreator.importTestData(sessionFactory);
    }

    @Test
    public void getUser() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = session.createQuery("select u from User u where u.email LIKE :vasya", User.class)
                    .setParameter("vasya", "%vasya%").getSingleResult();

            assertNotNull(user);
            assertThat(user.getEmail(), CoreMatchers.containsString("vasya"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void getCourse() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Course course = session.createQuery("select c from Course c where c.name LIKE :java", Course.class)
                    .setParameter("java", "%ava%").getSingleResult();

            assertNotNull(course);
            assertThat(course.getName(), CoreMatchers.containsString("Java"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void getCourseComment() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            CourseComments courseComments = session.createQuery("select c from CourseComments c where c.comment LIKE :java", CourseComments.class)
                    .setParameter("java", "%Bye%").getSingleResult();

            assertNotNull(courseComments);
            assertThat(courseComments.getComment(), CoreMatchers.containsString("Bye"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void getKBComment() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            KBComments kbComments = session.createQuery("select k from KBComments k where k.comment LIKE :java", KBComments.class)
                    .setParameter("java", "%body%").getSingleResult();

            assertNotNull(kbComments);
            assertThat(kbComments.getComment(), CoreMatchers.containsString("body"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void getKnowlegeBase() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            KnowlegeBase kbComments = session.createQuery("select k from KnowlegeBase k where k.text LIKE :java", KnowlegeBase.class)
                    .setParameter("java", "%rock%").getSingleResult();

            assertNotNull(kbComments);
            assertThat(kbComments.getText(), CoreMatchers.containsString("rock"));
            session.getTransaction().commit();
        }
    }

    @AfterClass
    public static void finish() {
        sessionFactory.close();
    }
}
