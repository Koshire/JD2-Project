package com.itacademy.akulov.utils;

import com.itacademy.akulov.dao.CourseDao;
import com.itacademy.akulov.dao.UserDao;
import com.itacademy.akulov.entity.Course;
import com.itacademy.akulov.entity.CourseType;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.entity.StudentUser;
import com.itacademy.akulov.entity.TeacherUser;
import com.itacademy.akulov.entity.User;
import com.itacademy.akulov.entity.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

public class ItemsCreator {

    private static ItemsCreator INSTANCE = new ItemsCreator();

    public static ItemsCreator getInstance() {
        return INSTANCE;
    }

    private UserDao userDao = UserDao.getInstance();
    private CourseDao courseDao = CourseDao.getInstance();

    public void importTestData(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {

            StudentUser vasya = StudentUser.builder()
                    .email("vasya@mail.ru")
                    .password("123")
                    .role(Role.STUDY)
                    .userData(UserData.builder()
                            .phone("375445002210")
                            .lastName("Пупкин")
                            .middleName("Витальевич")
                            .firstName("Василий")
                            .build())
                    .build();

            StudentUser petya = StudentUser.builder()
                    .email("petya@mail.ru")
                    .password("123")
                    .role(Role.STUDY)
                    .userData(UserData.builder()
                            .phone("375445002211")
                            .lastName("Петров")
                            .middleName("Александрович")
                            .firstName("Петр")
                            .build())
                    .build();

            StudentUser anna = StudentUser.builder()
                    .email("anna@mail.ru")
                    .password("123")
                    .role(Role.STUDY)
                    .userData(UserData.builder()
                            .phone("375445002212")
                            .lastName("Василькова")
                            .middleName("Петровна")
                            .firstName("Анна")
                            .build())
                    .build();

            TeacherUser viktor = TeacherUser.builder()
                    .email("viktor@mail.ru")
                    .password("123")
                    .role(Role.TEACH)
                    .userData(UserData.builder()
                            .phone("375445002213")
                            .lastName("Фишкин")
                            .middleName("Игоревич")
                            .firstName("Виктор")
                            .build())
                    .build();

            TeacherUser igor = TeacherUser.builder()
                    .email("igor@mail.ru")
                    .password("123")
                    .role(Role.TEACH)
                    .userData(UserData.builder()
                            .phone("375445002214")
                            .lastName("Лось")
                            .middleName("Валерьевич")
                            .firstName("Игорь")
                            .build())
                    .build();

            TeacherUser sveta = TeacherUser.builder()
                    .email("sveta@mail.ru")
                    .password("123")
                    .role(Role.TEACH)
                    .userData(UserData.builder()
                            .phone("375445002214")
                            .lastName("Синькевич")
                            .middleName("Павловна")
                            .firstName("Светлана")
                            .build())
                    .build();


            User admin =
                    new User(null, "ktaksv@gmail.com",
                            "123", Role.ADMIN, null, false);

            userDao.save(admin);
            userDao.save(sveta);
            userDao.save(igor);
            userDao.save(petya);
            userDao.save(anna);
            userDao.save(vasya);
            userDao.save(viktor);


            Course course1 = Course.builder()
                    .type(CourseType.FULL_TIME)
                    .description("Massive")
                    .duration(70)
                    .startDate(LocalDate.now())
                    .name("Java")
                    .plan("1,2,3")
                    .build();

            Course course2 = Course.builder()
                    .type(CourseType.FULL_TIME)
                    .description("Counter")
                    .duration(80)
                    .startDate(LocalDate.now())
                    .name("C#")
                    .plan("1,2,3,4")
                    .build();

            course1.getTeachers().add(igor);
            course1.getTeachers().add(viktor);
            course1.getStudents().add(petya);
            course1.getStudents().add(anna);
            course2.getStudents().add(vasya);
            course2.getTeachers().add(sveta);

            sveta.getCourses().add(course1);

/*            vasya.getResult().add(
                    new Result(new Result.UserCourse(vasya.getId(), course1.getId()),
                    5, course1, vasya));*/


            userDao.update(vasya);
            userDao.update(sveta);

            courseDao.save(course1);
            courseDao.save(course2);

/*             CourseComments courseComments1 = CourseComments.builder()
                    .course(course1)
                    .courseUser(sveta)
                    .comment("Hello World!!!")
                    .build();

            CourseComments courseComments2 = CourseComments.builder()
                    .course(course2)
                    .courseUser(viktor)
                    .comment("Bye bye World!!!")
                    .build();

            session.save(courseComments1);
            session.save(courseComments2);

            KnowlegeBase knowlegeBase1 = KnowlegeBase.builder()
                    .course(course1)
                    .userKnowlegeBase(petya)
                    .localDate(LocalDate.now())
                    .text("I see you!")
                    .build();

            KnowlegeBase knowlegeBase2 = KnowlegeBase.builder()
                    .course(course2)
                    .userKnowlegeBase(anna)
                    .localDate(LocalDate.now())
                    .text("We will rock you!")
                    .build();

            session.save(knowlegeBase1);
            session.save(knowlegeBase2);

            KBComments kbComments1 = KBComments.builder()
                    .courseUser(igor)
                    .comment("Ku-Ku")
                    .knowlegeBase(knowlegeBase1)
                    .build();

            KBComments kbComments2 = KBComments.builder()
                    .courseUser(vasya)
                    .comment("No body Ku-Ku")
                    .knowlegeBase(knowlegeBase2)
                    .build();

            session.save(kbComments1);
            session.save(kbComments2);
*/
        }
    }
}
