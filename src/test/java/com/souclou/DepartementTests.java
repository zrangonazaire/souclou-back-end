package com.souclou;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
public class DepartementTests {
    @SuppressWarnings("rawtypes")
    @Container
    static MySQLContainer<?> mySQLContainer=new MySQLContainer("mysql:latest")  ;
   @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username",mySQLContainer::getUsername);
        registry.add("spring.datasource.password",mySQLContainer::getPassword);

    }
    @BeforeAll
    static void beforeAll(){
        mySQLContainer.start();
    }
    @AfterAll
    static void afterAll(){
        mySQLContainer.stop();
    }
}
