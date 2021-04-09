package com.surveyMonkey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.*;
import com.surveyMonkey.repository.SurveyRepository;
import com.surveyMonkey.util.FakeTestSurvey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ViewTest {
    @Autowired
    private WebApplicationContext wac;
    private WebClient webClient;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        webClient = MockMvcWebClientBuilder.webAppContextSetup(wac).build();
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
    }

    @Test
    public void testDynamicChanges() throws IOException {
        String url = "https://localhost:8080/surveyQuestions?title=test";
        HtmlPage page = webClient.getPage(url);
        HtmlNumberInput numberInput = page.getHtmlElementById("numQuestions");
        numberInput.setValueAttribute("13");
        HtmlButton button = page.getHtmlElementById("generateQuestions");
        button.click();
        //page updated
        System.out.println(page.asText());
        assertTrue(page.getHtmlElementById("question-div12").isDisplayed());
        assertThrows(ElementNotFoundException.class, () -> {
            page.getHtmlElementById("question-div13");
        });
    }

    @Test
    public void testIncorrectPassword() throws IOException {
        FakeTestSurvey fts = new FakeTestSurvey();
        surveyRepository.save(fts.getTestSurvey());
        String url = "https://localhost:8080/home";
        HtmlPage page = webClient.getPage(url);
        HtmlTextInput textInput = page.getHtmlElementById("surveyCode");
        textInput.setValueAttribute("reserved");
        HtmlPasswordInput textInput2 = page.getHtmlElementById("surveyPassword");
        textInput2.setValueAttribute("test");
        HtmlButton button = page.getHtmlElementById("search");
        HtmlPage page2 = button.click();
        webClient.waitForBackgroundJavaScript(2000);
        assertTrue(page2.asText().contains("Sorry, wrong password or non-existent survey"));
        surveyRepository.delete(fts.getTestSurvey());
    }
    @Test
    public void testCorrectPassword() throws IOException {
        FakeTestSurvey fts = new FakeTestSurvey();
        surveyRepository.save(fts.getTestSurvey());
        String url = "https://localhost:8080/home";
        HtmlPage page = webClient.getPage(url);
        HtmlTextInput textInput = page.getHtmlElementById("surveyCode");
        textInput.setValueAttribute("reserved");
        HtmlPasswordInput textInput2 = page.getHtmlElementById("surveyPassword");
        textInput2.setValueAttribute("reserved");
        HtmlButton button = page.getHtmlElementById("search");
        HtmlPage page2 = button.click();
        webClient.waitForBackgroundJavaScript(2000);
        assertTrue(page2.getHtmlElementById("results").isDisplayed());
        surveyRepository.delete(fts.getTestSurvey());
    }





}
