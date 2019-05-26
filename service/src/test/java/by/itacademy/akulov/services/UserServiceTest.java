package by.itacademy.akulov.services;

import org.junit.Test;

public class UserServiceTest {

    UserService userService = UserService.getInstance();

    @Test
    public void getAll() {
        System.out.println(userService.getAll());
    }
}