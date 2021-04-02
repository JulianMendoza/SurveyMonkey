package com.surveyMonkey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surveyMonkey.entities.OpenEndedQuestion;
import com.surveyMonkey.entities.QuestionAnswerWrapper;
import com.surveyMonkey.entities.Survey;
import com.surveyMonkey.repository.SurveyRepository;
import com.surveyMonkey.util.DataRetrieval;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SurveyRepository surveyRepository;

    /**
     * Test /survey/{surveyCode} endpoint with a non existing code
     */
    @Test
    public void getNonExistantSurveyPageTest() throws Exception {
        this.mockMvc.perform(get("/survey/non-existant-survey-code")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sorry, there is no survey with that link.")));
    }

    /**
     * Test /survey/{surveyCode} endpoint with an existing code
     */
    @Test
    public void getExistingSurveyPageTest() throws Exception {
        Survey survey = new Survey("Test Survey", "password");
        survey.setSurveyCode("TestCode");
        surveyRepository.save(survey);
        this.mockMvc.perform(get("/survey/TestCode")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(survey.getTitle())));
        surveyRepository.delete(survey);
    }

    /**
     * Test /surveyQuestions endpoint with a title
     */
    @Test
    public void surveyCreationTitleTest() throws Exception {
        this.mockMvc.perform(get("/surveyQuestions?title=coolbeans")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("coolbeans")));
    }

    /**
     * Test /surveyResults endpoint with a valid survey code
     */
    @Test
    public void surveyResultPageTest() throws Exception {
        Survey survey = new Survey("test", "test");
        System.out.println(survey.getSurveyCode());
        surveyRepository.save(survey);
        String url = "/surveyResults?surveyCode=" + survey.getSurveyCode() + "&surveyPassword=test";

        this.mockMvc.perform(post(url)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(survey.getSurveyCode())));
        surveyRepository.delete(survey);
    }

    /**
     * Test /surveyResult endpoint with a survey
     */
    @Test
    public void surveyResultJSONTest() throws Exception {
        Survey survey = new Survey("test", "test");
        List<QuestionAnswerWrapper> qa = new ArrayList<>();
        qa.add(new QuestionAnswerWrapper(new OpenEndedQuestion("What is life?")));
        survey.setSurvey(qa);
        surveyRepository.save(survey);
        DataRetrieval dr=new DataRetrieval();
        dr.setData(survey.getSurveyCode());
        String str = asJsonString(dr);
        this.mockMvc.perform(post("/surveyResult").contentType(MediaType.APPLICATION_JSON).content(str))
                .andExpect(content().string(containsString("What is life?")));
        surveyRepository.delete(survey);
    }

    /**
     * https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}