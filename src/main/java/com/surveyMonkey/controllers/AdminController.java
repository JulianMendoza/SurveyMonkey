package com.surveyMonkey.controllers;

import static com.surveyMonkey.util.Constants.HISTOGRAM;
import static com.surveyMonkey.util.Constants.OPEN_ENDED;
import static com.surveyMonkey.util.Constants.OPTION;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surveyMonkey.entities.Answer;
import com.surveyMonkey.entities.HistoQuestion;
import com.surveyMonkey.entities.OpenEndedQuestion;
import com.surveyMonkey.entities.OptionQuestion;
import com.surveyMonkey.entities.QuestionAnswerWrapper;
import com.surveyMonkey.entities.Survey;
import com.surveyMonkey.repository.SurveyRepository;
import com.surveyMonkey.util.AnswerHelper;
import com.surveyMonkey.util.DataRetrieval;
import com.surveyMonkey.util.QuestionHelper;
import com.surveyMonkey.util.ResponseHelper;
import com.surveyMonkey.util.SurveyHelper;

@Controller
public class AdminController {
    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping({"/"})
    public String home(Model model) {
        return "index";
    }

    @GetMapping({"/surveyTest", "/home"})
    public String createSurvey() {
        return "creation";
    }

    @GetMapping({"/surveyQuestions"})
    public String createQuestions(@RequestParam("title") String title, Model model) {
        model.addAttribute("title", title);
        return "questions";
    }

    @GetMapping("/survey/{surveyCode}")
    public String showSurvey(@PathVariable String surveyCode, Model model) {
        Survey survey = new Survey();
        for (Survey s : surveyRepository.findAll()) {
            if (s.getSurveyCode().equals(surveyCode)) {
                survey = s;
            }
        }
        model.addAttribute(survey);
        return "show";
    }

    @PostMapping({"/surveyResults"})
    public String surveyResult(@RequestParam("surveyCode") String surveyCode, @RequestParam("surveyPassword") String surveyPassword, Model model) {
        for(Survey survey:surveyRepository.findAll()){
            if(survey.getSurveyCode().equals(surveyCode)&&survey.getSurveyPassword().equals(surveyPassword)){
                model.addAttribute("surveyCode", surveyCode);
                break;
            }
        }
        return "results";
    }

    @PostMapping({"/surveyResult"})
    @ResponseBody
    public List<QuestionAnswerWrapper> surveyResult(@RequestBody DataRetrieval dataRetrieval) {
        for (Survey survey : surveyRepository.findAll()) {
            if (survey.getSurveyCode().equals(dataRetrieval.getData())) {
                return survey.getSurvey();
            }
        }
        return null;
    }

    @PostMapping({"/create"})
    @ResponseBody
    public ResponseHelper createSurvey(@RequestBody SurveyHelper surveyHelper, Model model) {
        Survey survey = new Survey(surveyHelper.getTitle(), surveyHelper.getPassword());
        for (QuestionHelper q : surveyHelper.getQuestions()) {
            switch (q.getQuestionType()) {
                case OPEN_ENDED:
                    survey.setQuestion(new OpenEndedQuestion(q.getQuestion()));
                    break;
                case HISTOGRAM:
                    survey.setQuestion(new HistoQuestion(q.getQuestion(), q.getMinVal(), q.getMaxVal(), q.getStepSize()));
                    break;
                case OPTION:
                    survey.setQuestion(new OptionQuestion(q.getQuestion(), q.getChoices()));
                    break;
            }
        }
        surveyRepository.save(survey);
        model.addAttribute("survey", survey);
        return new ResponseHelper(survey.getSurveyCode(), survey.getTitle());
    }

    @PostMapping({"/answersStored"})
    @ResponseBody
    public void answerLinkedQuestion(@RequestBody AnswerHelper answerHelper) {
        for (Survey s : surveyRepository.findAll()) {
            if (s.getSurveyCode().equals(answerHelper.getSurveyCode())) {
                for (int i = 0; i < answerHelper.getAnsweredStored().size(); i++) {
                    if (s.getSurvey().get(i).getqWrapperid() == answerHelper.getAnsweredStored().get(i).getQuestionId()) {
                        s.getSurvey().get(i).getAnswers().add(new Answer(answerHelper.getAnsweredStored().get(i).getAnswer()));
                        surveyRepository.save(s);
                    }
                }
            }
        }
    }
}