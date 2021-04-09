package com.surveyMonkey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surveyMonkey.entities.Survey;
import com.surveyMonkey.repository.SurveyRepository;
import com.surveyMonkey.util.AnswerHelper;
import com.surveyMonkey.util.QuestionHelper;
import com.surveyMonkey.util.StoreAnswerHelper;
import com.surveyMonkey.util.SurveyHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.surveyMonkey.util.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MongoRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    SurveyRepository surveyRepository;

    private static Survey mockSurvey;

    @Before
    public void setUp() throws Exception {
        mockSurvey=new Survey("Best Survey Ever","password123");
        assertTrue(mockSurvey.getSurveyPassword().equals("password123"));
    }

    @After
    public void tearDown() throws Exception {
        for(Survey s1:surveyRepository.findAll()){
            surveyRepository.delete(s1);
        }
    }

    @Test
    public void surveyInitTest(){
        List<Survey> surveys=new ArrayList<>();
        surveyRepository.findAll().forEach(surveys::add);
        assertTrue(surveys.size()==0);
    }

    @Test
    public void shouldBeEmpty() {
        assertTrue(surveyRepository.findAll().isEmpty());
    }

    @Test
    public void surveyCreation() throws Exception {
        SurveyHelper sh=new SurveyHelper();
        sh.setPassword(mockSurvey.getSurveyPassword());
        sh.setTitle(mockSurvey.getTitle());
        sh.setQuestions(createQuestionHelper());
        this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON).content(asJsonString(sh)));
        List<Survey> surveys=new ArrayList<>();
        surveyRepository.findAll().forEach(surveys::add);
        assertTrue(surveys.size()==1);
        assertTrue(surveys.get(0).getTitle().equals(mockSurvey.getTitle()));
        assertTrue(surveys.get(0).getSurvey().size()==3);
        assertTrue(surveys.get(0).getSurvey().get(0).getQuestion().getQuestion().equals("What is green?"));
        assertTrue(surveys.get(0).getSurvey().get(1).getQuestion().getQuestion().equals("How are you today? (1-100)"));
        assertTrue(surveys.get(0).getSurvey().get(2).getQuestion().getQuestion().equals("What color is the best?"));
        AnswerHelper ah=new AnswerHelper();
        ah.setSurveyCode(surveys.get(0).getSurveyCode());
        ah.setAnsweredStored(createStoreAnswerHelper());
        this.mockMvc.perform(post("/answersStored").contentType(MediaType.APPLICATION_JSON).content(asJsonString(ah)));
        surveys=new ArrayList<>();
        surveyRepository.findAll().forEach(surveys::add);
        assertTrue(surveys.size()==1);
        assertTrue(surveys.get(0).getTitle().equals(mockSurvey.getTitle()));
        assertTrue(surveys.get(0).getSurvey().size()==3);
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
    private List<QuestionHelper> createQuestionHelper(){
        List<QuestionHelper> qhList=new ArrayList<>();
        QuestionHelper qh=new QuestionHelper();
        qh.setQuestion("What is green?");
        qh.setQuestionType(OPEN_ENDED);
        qhList.add(qh);
        qh=new QuestionHelper();
        qh.setQuestion("How are you today? (1-100)");
        qh.setMaxVal(1);
        qh.setMaxVal(100);
        qh.setStepSize(1);
        qh.setQuestionType(HISTOGRAM);
        qhList.add(qh);
        qh=new QuestionHelper();
        qh.setQuestion("What color is the best?");
        qh.setChoices(new ArrayList<>(Arrays.asList(new String[]{"Green","Blue","Red"})));
        qh.setQuestionType(OPTION);
        qhList.add(qh);
        return qhList;
    }
    private List<StoreAnswerHelper> createStoreAnswerHelper(){
        List<StoreAnswerHelper> sahList = new ArrayList<>();
        StoreAnswerHelper sah=new StoreAnswerHelper();
        sah.setAnswer("grass");
        sah.setQuestionId("2");
        sahList.add(sah);
        sah=new StoreAnswerHelper();
        sah.setAnswer("99");
        sah.setQuestionId("4");
        sahList.add(sah);
        sah=new StoreAnswerHelper();
        sah.setAnswer("Blue");
        sah.setQuestionId("6");
        sahList.add(sah);
        return sahList;
    }

}
