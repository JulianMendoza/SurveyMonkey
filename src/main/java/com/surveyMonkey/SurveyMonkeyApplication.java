package com.surveyMonkey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class SurveyMonkeyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyMonkeyApplication.class, args);
	}

}