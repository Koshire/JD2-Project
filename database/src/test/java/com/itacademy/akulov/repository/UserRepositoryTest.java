package com.itacademy.akulov.repository;

import com.itacademy.akulov.config.PersistenceConfig;
import com.itacademy.akulov.entity.CourseComments;
import com.itacademy.akulov.entity.Result;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.entity.StudentUser;
import com.itacademy.akulov.entity.User;
import com.itacademy.akulov.entity.UserData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void before2() {

        List<Result> list = new ArrayList<>();
        List<CourseComments> comments = new ArrayList<>();
        StudentUser studentUser = StudentUser.builder()
                .id(100L)
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
                .blockList(false)
                .build();
        studentUser.setResult(list);
        studentUser.setCourseComments(comments);
        studentUserRepository.save(studentUser);
    }

    @Test
    public void test5() {
        Assert.assertTrue(userRepository.findByEmail("admin@gmail.com").isPresent());
        Assert.assertFalse(userRepository.findByEmail("jora@gmail.com").isPresent());
    }

    @Test
    public void test6() {
        User user = userRepository.findByEmail("admin@gmail.com").get();
        Assert.assertEquals(user.getRole(), Role.ADMIN);
        Assert.assertFalse(user.getBlockList());
        Assert.assertNotNull(user.getCourseComments());
        Assert.assertNotNull(user.getUserData());
        Assert.assertEquals(user.getEmail(), "admin@gmail.com");
        Assert.assertEquals(user.getPassword(), "55");
        Assert.assertEquals(user.getUserData().getFirstName(), "55");
        Assert.assertEquals(user.getUserData().getLastName(), "55");
        Assert.assertEquals(user.getUserData().getMiddleName(), "55");
        Assert.assertEquals(user.getUserData().getPhone(), "55");
    }
}