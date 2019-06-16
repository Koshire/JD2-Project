package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.services.config.ConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ConfigTest.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testMeth() {
        FindDto findDto = FindDto.builder()
                .page(1L)
                .pages(1L)
                .viewLimit(5L)
                .build();
        userService.getSizeByCustom(findDto);
    }
}