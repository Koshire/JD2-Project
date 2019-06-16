package com.itacademy.akulov.servlets;

import com.itacademy.akulov.config.ConfTest;
import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ConfTest.class)
@Transactional
public class AllLoginsServletTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        FindDto findDto = FindDto.builder()
                .page(1L)
                .viewLimit(5L)
                .build();
        System.out.println(userService.getSizeByCustom(findDto));

    }
}