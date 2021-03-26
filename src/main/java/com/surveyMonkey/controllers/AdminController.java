package com.surveyMonkey.controllers;

import com.surveyMonkey.entities.*;
import com.surveyMonkey.repository.SurveyRepository;
import com.surveyMonkey.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.surveyMonkey.util.Constants.*;

@Controller
public class AdminController {
	@Autowired
	private SurveyRepository surveyRepository;

	@GetMapping({ "/" })
	public String home(Model model) {
		return "index";
	}

	@GetMapping({ "/surveyTest", "/home" })
	public String createSurvey() {
		return "creation";
	}

	@GetMapping({ "/surveyQuestions" })
	public String createQuestions(@RequestParam("title") String title, Model model) {
		model.addAttribute("title", title);
		return "questions";
	}

	@PostMapping("/survey")
	public String listSurvey(@RequestParam("title") String title, Model model) {
		return "questions";
	}

	@GetMapping("/survey/{surveyCode}")
	public String showSurvey(@PathVariable String surveyCode, Model model){
		Survey survey = new Survey();
		for (Survey s : surveyRepository.findAll()) {
			if(s.getSurveyCode().equals(surveyCode)){
				survey = s;
			}
		}
		model.addAttribute( survey);
		return "show";
	}


	@PostMapping({ "/surveyResults" })
	public String surveyResult() {
		return "results";
	}

	@PostMapping({ "/create" })
	@ResponseBody
	public ResponseHelper createSurvey(@RequestBody SurveyHelper surveyHelper, Model model) {
		Survey survey = new Survey(surveyHelper.getTitle(), surveyHelper.getPassword());
		for (QuestionHelper q : surveyHelper.getQuestions()) {
			switch (q.getQuestionType()) {
				case OPEN_ENDED:
					survey.setQuestion(new OpenEndedQuestion(q.getQuestion()));
					System.out.println(q.getQuestion());
					break;
				case HISTOGRAM:
					survey.setQuestion(new HistoQuestion(q.getQuestion(), q.getMinVal(), q.getMaxVal(), q.getStepSize()));
					System.out.println(q.getQuestion());
					break;
				case OPTION:
					survey.setQuestion(new OptionQuestion(q.getQuestion(), q.getChoices()));
					for (String s : q.getChoices()) {
						System.out.println(s);
					}
					break;
			}
		}
		System.out.println("PASSWORD:" + surveyHelper.getPassword());
		if (survey.getTitle().equals("test")) {
			for (QuestionAnswerWrapper q : survey.getSurvey()) {
				System.out.println(q.getQuestion());
			}
		}
		surveyRepository.save(survey);
		for (Survey s : surveyRepository.findAll()) {
			if (s.getTitle().equals("test")) {
				for (QuestionAnswerWrapper q : s.getSurvey()) {
					System.out.println(q.getQuestion());
				}
			}
		}

		model.addAttribute("survey",survey);

		return new ResponseHelper(survey.getSurveyCode(),survey.getTitle());
	}

	@GetMapping("/testQuestions")
	@ResponseBody
	public Survey viewQuestions(@RequestParam("title") String title) {
		for (Survey s : surveyRepository.findAll()) {
			return s;
		}
		return null;
	}

	@PostMapping({"/answersStored"})
	@ResponseBody
	public void answerLinkedQuestion(@RequestBody AnswerHelper answerHelper, Model model) {

		for(Survey s: surveyRepository.findAll()){
			if(s.getSurveyCode().equals(answerHelper.getSurveyCode())){
				for(int i =0; i< answerHelper.getAnsweredStored().size();i++){
					if(s.getSurvey().get(i).getqWrapperid() == answerHelper.getAnsweredStored().get(i).getQuestionId()){
						s.getSurvey().get(i).getAnswers().add(new Answer(answerHelper.getAnsweredStored().get(i).getAnswer()));
						surveyRepository.save(s);
						System.out.println(answerHelper.getAnsweredStored().get(i).getAnswer());
					}
				}
			}
		}
	}



}