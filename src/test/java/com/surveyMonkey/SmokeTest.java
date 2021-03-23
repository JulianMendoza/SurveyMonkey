package com.surveyMonkey;

import com.surveyMonkey.controllers.AdminController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private AdminController controller;
    @BeforeAll
    public void init(){

    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}