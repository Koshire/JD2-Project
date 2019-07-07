package com.itacademy.akulov.repository;

import com.itacademy.akulov.config.PersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
@Transactional
public class StudentUserRepositoryTest {

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Test
    public void test1(){
        System.out.println(studentUserRepository.findById(1L));
    }
}