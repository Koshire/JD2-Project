package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.entity.Role;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {

    UserService userService = UserService.getInstance();

    @Test
    public void getAll() {

        String role = "ADMIN";

        FindDto findDto = FindDto.builder()
                .blockList(false)
                .fio("")
                .limit(5L)
                .offset(0L)
                .build();
        if (!role.isEmpty()) {
            findDto.setRole(Role.valueOf(role));
        }

        List<LoginDto> list = userService.getByCustomLO(findDto);

/*        Assert.assertThat();*/
    }
}