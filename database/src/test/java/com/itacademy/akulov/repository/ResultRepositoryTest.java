package com.itacademy.akulov.repository;

import com.itacademy.akulov.config.PersistenceConfig;
import com.itacademy.akulov.entity.Course;
import com.itacademy.akulov.entity.CourseType;
import com.itacademy.akulov.entity.Result;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.entity.StudentUser;
import com.itacademy.akulov.entity.UserData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
@Transactional
public class ResultRepositoryTest {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Before
    public void before3() {
        Course course = courseRepository.save(Course.builder()
                .id(1L)
                .type(CourseType.FULL_TIME)
                .plan("hello")
                .name("jd1")
                .duration(70)
                .description("Helllo1")
                .startDate(LocalDate.parse("2019-07-20"))
                .build());

        StudentUser studentUser = studentUserRepository.save(StudentUser.builder()
                .id(1L)
                .email("admin@gmail.com")
                .role(Role.ADMIN)
                .password("55")
                .id(1L)
                .userData(UserData.builder()
                        .lastName("55")
                        .middleName("55")
                        .firstName("55")
                        .phone("55")
                        .build())
                .build());

        Result result = resultRepository.save(Result.builder()
                .userCourse(Result.UserCourse.builder()
                        .courseId(1L)
                        .userId(1L)
                        .build())
                .userResult(studentUser)
                .result(5)
                .course(course)
                .build());
        System.out.println();
    }

    @Test
    public void test7(){
        List<Result> list = resultRepository.findByCourseId(1L);
        Assert.assertEquals(list.get(0).getResult(), 5);
        Assert.assertNotNull(list.get(0).getUserResult());
        Assert.assertNotNull(list.get(0).getCourse());
        Assert.assertTrue(list.get(0).getUserCourse().getUserId() > 0);
        Assert.assertTrue(list.get(0).getUserCourse().getCourseId() > 0);
    }
}