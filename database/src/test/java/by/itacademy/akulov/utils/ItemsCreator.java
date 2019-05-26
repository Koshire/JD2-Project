package by.itacademy.akulov.utils;

import by.itacademy.akulov.entity.BlockList;
import by.itacademy.akulov.entity.Course;
import by.itacademy.akulov.entity.CourseComments;
import by.itacademy.akulov.entity.CourseType;
import by.itacademy.akulov.entity.KBComments;
import by.itacademy.akulov.entity.KnowlegeBase;
import by.itacademy.akulov.entity.Role;
import by.itacademy.akulov.entity.StudentUser;
import by.itacademy.akulov.entity.TeacherUser;
import by.itacademy.akulov.entity.User;
import by.itacademy.akulov.entity.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

public class ItemsCreator {

    private static ItemsCreator INSTANCE = new ItemsCreator();

    public static ItemsCreator getInstance() {
        return INSTANCE;
    }

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

            BlockList blockList = BlockList.builder()
                    .userId(sveta.getId())
                    .build();

            session.save(blockList);

            User admin =
                    new User(null ,"admin@mail.ru",
                            "1234", Role.ADMIN, null);

            session.save(admin);
            session.save(sveta);
            session.save(igor);
            session.save(petya);
            session.save(anna);
            session.save(vasya);
            session.save(viktor);

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
            vasya.getCourses().add(course2);
            vasya.getCoursesResult().put(course2, 5);

            session.save(course1);
            session.save(course2);

            CourseComments courseComments1 = CourseComments.builder()
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
                    .courseUser(petya)
                    .localDate(LocalDate.now())
                    .text("I see you!")
                    .build();

            KnowlegeBase knowlegeBase2 = KnowlegeBase.builder()
                    .course(course2)
                    .courseUser(anna)
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
        }
    }
}
