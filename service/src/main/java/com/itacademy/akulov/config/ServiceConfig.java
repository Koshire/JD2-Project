package com.itacademy.akulov.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.itacademy.akulov.services")
@EnableTransactionManagement(proxyTargetClass = true)
@Import(PersistenceConfig.class)
public class ServiceConfig {

}
