package com.surveyMonkey;
import com.surveyMonkey.entities.Survey;
import com.surveyMonkey.repository.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


    @Test
    public void getNonExistantSurveyPageTest() throws Exception {
        this.mockMvc.perform(get("/survey/non-existant-survey-code")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sorry, there is no survey with that link.")));
    }

    @Test
    public void getExistingSurveyPageTest() throws Exception {
        Survey survey = new Survey("Test Survey", "password");
        survey.setSurveyCode("TestCode");
        surveyRepository.save(survey);
        this.mockMvc.perform(get("/survey/TestCode")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(survey.getTitle())));
        surveyRepository.delete(survey);
    }

}