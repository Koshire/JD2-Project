package com.itacademy.akulov.entity;

import com.itacademy.akulov.dao.CourseDao;
import com.itacademy.akulov.dao.ResultDao;
import com.itacademy.akulov.dao.UserDao;
import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.utils.SessionManager;
import org.hamcrest.CoreMatchers;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestUserDao {

    private UserDao userDao = UserDao.getInstance();
    private CourseDao courseDao = CourseDao.getInstance();
    private ResultDao resultDao = ResultDao.getInstance();

    @BeforeClass
    public static void fillItems() {

    }

    @Test
    public void getUser() {
        User userTest = new User(null, "Kot@gmail.com",
                "123", Role.STUDY,
                new UserData("Kot", "kot", "kot", null),
                false);

        User userTest2 = new User(null, "bear@gmail.com",
                "123", Role.STUDY,
                new UserData("bear", "bear", "bear", null),
                false);
        userDao.save(userTest);
        userDao.save(userTest2);

        Course course = Course.builder()
                .description("la - la- la")
                .duration(70)
                .name("Java")
                .plan("Ky ky ")
                .type(CourseType.FULL_TIME)
                .build();

        courseDao.save(course);

        assertNotNull(course);

        User user = userDao.get(userTest.getId()).get();
        assertNotNull(user);
        assertThat(user.getEmail(), CoreMatchers.containsString("Kot"));

        user.setEmail("Sobakt@gmail.com");
        userDao.update(user);

        User userUpd = userDao.get(user.getId()).get();

        assertNotNull(userUpd);
        assertThat(userUpd.getEmail(), CoreMatchers.containsString("Sobakt"));

        Session session = SessionManager.getSession();
        FindDto findDto = FindDto.builder()
                .limit(10L)
                .offset(0L)
                .role(Role.STUDY)
                .fio("")
                .blockList(false)
                .build();
        Long size = userDao.size(userDao.findByQueue(session, findDto));

        assertTrue(size > 0);

        List<User> list = userDao.getByQueryLO(userDao.findByQueue(session, findDto), 10L, 0L);

        assertTrue(list.size() > 0);

        List<User> listAll = userDao.getAll();

        assertEquals(2, listAll.size());

        StudentUser studentUser = StudentUser.builder()
                .email("muha")
                .blockList(false)
                .password("123")
                .role(Role.STUDY)
                .userData(UserData.builder()
                        .firstName("muha")
                        .middleName("muha")
                        .lastName("muha")
                        .build()
                )
                .build();
        userDao.save(studentUser);

        assertTrue(studentUser.getId() > 0);

        StudentUser studentUser1 = (StudentUser) userDao.get(studentUser.getId()).get();

        assertNotNull(studentUser);

        studentUser1.getResult().add(Result.builder()
                .userCourse(new Result.UserCourse(studentUser1.getId(), course.getId()))
                .userResult(studentUser1)
                .course(course)
                .result(5)
                .build());

        userDao.update(studentUser1);

        StudentUser studentUser2 = (StudentUser) userDao.get(studentUser1.getId()).get();

        assertTrue(studentUser2.getResult().size()>0);

        Optional<Result> result = resultDao.get(new Result.UserCourse(studentUser2.getId(), course.getId()));

        assertEquals(5, result.get().getResult());



    }

    @AfterClass
    public static void finish() {

    }
}
