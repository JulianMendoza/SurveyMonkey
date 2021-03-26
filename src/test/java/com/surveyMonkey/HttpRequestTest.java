package com.surveyMonkey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Survey Monkey");
    }
    @Test
    public void testHomePage() throws Exception{
            assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/home",
                    String.class)).contains("Create a new survey");
    }
    @Test
    public void testSurveyCreationPage() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/surveyQuestions?title=test",
                String.class)).contains("Create your survey!");
    }
}
