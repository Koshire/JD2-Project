package com.itacademy.akulov.config;

import com.itacademy.akulov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.itacademy.akulov.controller"})
@Import(value = {InternationalizationConfig.class, ThymeleafConfig.class})
public class WebConfig {

    private UserService userService;

    @Autowired
    public WebConfig(UserService userService) {
        this.userService = userService;
    }

}
