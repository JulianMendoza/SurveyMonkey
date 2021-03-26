package com.surveyMonkey;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlNumberInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
public class ViewTest {
    @Autowired
    private WebApplicationContext wac;
    private WebClient webClient;
    @BeforeEach
    public void setUp(){
        webClient = MockMvcWebClientBuilder.webAppContextSetup(wac).build();
    }
    @Test
    public void testDynamicChanges() throws IOException {
        String url="https://localhost:8080/surveyQuestions?title=test";
        HtmlPage page=webClient.getPage(url);
        HtmlNumberInput numberInput=page.getHtmlElementById("numQuestions");
        numberInput.setValueAttribute("13");
        HtmlButton button=page.getHtmlElementById("generateQuestions");
        button.click();
        //page updated
        assertTrue(page.getHtmlElementById("question-div12").isDisplayed());
        assertThrows(ElementNotFoundException.class,()->{
            page.getHtmlElementById("question-div13");});
    }
}
