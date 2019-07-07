package com.itacademy.akulov.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.itacademy.akulov.repository")
@Import(PersistenceConfig.class)
public class TestConfiguration {

}
