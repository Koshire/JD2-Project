package com.itacademy.akulov.services;

import com.itacademy.akulov.config.ServiceConfig;
import com.itacademy.akulov.repository.StudentUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@Transactional
public class StudentUserServiceTest {

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Test
    public void test1(){
        System.out.println(studentUserRepository.findById(4L));
    }
}