package com.itacademy.akulov.services.config;

import com.itacademy.akulov.config.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.itacademy.akulov.services")
@Import(ServiceConfig.class)
public class ConfigTest {

}
