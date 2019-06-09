package com.itacademy.akulov.services.config;

import com.itacademy.akulov.config.Config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.itacademy.akulov")
@Import(Config.class)
public class ConfigTest {

}
