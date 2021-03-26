package com.surveyMonkey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surveyMonkey.entities.*;
import com.surveyMonkey.repository.SurveyRepository;
import com.surveyMonkey.util.AnswerHelper;
import com.surveyMonkey.util.StoreAnswerHelper;

import com.surveyMonkey.util.SurveyHelper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SurveyMonkeyEntityTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private SurveyRepository surveyRepository;
	private Survey mockSurvey;
	@Test
	@Order(1)
	public void init(){
		List<Survey> surveys=new ArrayList<>();
		surveyRepository.findAll().forEach(surveys::add);
		assertThat(surveys.size()==0);
		mockSurvey=new Survey("Best Survey Ever","password123");
		List<QuestionAnswerWrapper> qa=new ArrayList<>();
		qa.add(new QuestionAnswerWrapper(new OpenEndedQuestion("What is green?")));
		qa.add(new QuestionAnswerWrapper(new HistoQuestion("How are you today? (1-100)",1,100,1)));
		qa.add(new QuestionAnswerWrapper(new OptionQuestion("What color is the best?",new ArrayList<>(Arrays.asList(new String[]{"Green","Blue","Red"})))));
		mockSurvey.setSurvey(qa);
	}
	@Test
	@Order(2)
	public void surveyCreation() throws Exception {
		SurveyHelper sh=new SurveyHelper();
		//sh.setPassword(mockSurvey.getSurveyPassword());
		//sh.setTitle(mockSurvey.getTitle());
		//List<String> q= new ArrayList<>();
		//this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON).content(asJsonString(mockSurvey.getSurvey())));
		List<Survey> surveys=new ArrayList<>();
		surveyRepository.findAll().forEach(surveys::add);
		//assertThat(surveys.size()==1);
		//assertThat(surveys.get(0).getTitle().equals(mockSurvey.getTitle()));
		//assertThat(surveys.get(0).getSurvey().size()==mockSurvey.getSurvey().size());
	}
	@Test
	@Order(3)
	public void surveyAnswer() throws Exception {
		AnswerHelper ah=new AnswerHelper();
		//ah.setSurveyCode(mockSurvey.getSurveyCode());
		List<StoreAnswerHelper> sahList = new ArrayList<>();
		StoreAnswerHelper sah=new StoreAnswerHelper();
		sah.setAnswer("grass");
		sah.setQuestionId(0);
		sahList.add(sah);
		sah.setAnswer("99");
		sah.setQuestionId(1);
		sahList.add(sah);
		sah.setAnswer("Blue");
		sah.setQuestionId(2);
		sahList.add(sah);
		ah.setAnsweredStored(sahList);
		this.mockMvc.perform(post("/answersStored").contentType(MediaType.APPLICATION_JSON).content(asJsonString(ah)));
		List<Survey> surveys=new ArrayList<>();
		surveyRepository.findAll().forEach(surveys::add);
		//assertThat(surveys.get(0).getSurvey()!=null);
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