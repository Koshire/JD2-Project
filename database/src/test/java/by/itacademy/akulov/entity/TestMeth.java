package by.itacademy.akulov.entity;

import by.itacademy.akulov.utils.ItemsCreator;
import org.hamcrest.CoreMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestMeth {

    private ItemsCreator itemsCreator = ItemsCreator.getInstance();
    private SessionFactory sessionFactory;

    @Before
    public void fillItems() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        itemsCreator.importTestData(sessionFactory);
    }

    @Test
    public void getUser() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = session.createQuery("select u from User u where u.email LIKE :vasya", User.class)
                    .setParameter("vasya", "%vasya%").getSingleResult();

            Assert.assertNotNull(user);
            Assert.assertThat(user.getEmail(), CoreMatchers.containsString("vasya"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void getCourse() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Course course = session.createQuery("select c from Course c where c.name LIKE :java", Course.class)
                    .setParameter("java", "%ava%").getSingleResult();

            Assert.assertNotNull(course);
            Assert.assertThat(course.getName(), CoreMatchers.containsString("Java"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void getCourseComment() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            CourseComments courseComments = session.createQuery("select c from CourseComments c where c.comment LIKE :java", CourseComments.class)
                    .setParameter("java", "%Bye%").getSingleResult();

            Assert.assertNotNull(courseComments);
            Assert.assertThat(courseComments.getComment(), CoreMatchers.containsString("Bye"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void getKBComment() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            KBComments kbComments = session.createQuery("select k from KBComments k where k.comment LIKE :java", KBComments.class)
                    .setParameter("java", "%body%").getSingleResult();

            Assert.assertNotNull(kbComments);
            Assert.assertThat(kbComments.getComment(), CoreMatchers.containsString("body"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void getKnowlegeBase() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            KnowlegeBase kbComments = session.createQuery("select k from KnowlegeBase k where k.text LIKE :java", KnowlegeBase.class)
                    .setParameter("java", "%rock%").getSingleResult();

            Assert.assertNotNull(kbComments);
            Assert.assertThat(kbComments.getText(), CoreMatchers.containsString("rock"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void getBlockList() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<BlockList> blockList = session.createQuery("select b from BlockList b", BlockList.class)
                    .getResultList();

            Assert.assertNotNull(blockList);
            Assert.assertTrue(blockList.size() > 0);
            session.getTransaction().commit();
        }
    }

    @After
    public void finish() {
        sessionFactory.close();
    }
}
