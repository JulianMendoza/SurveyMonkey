package com.surveyMonkey.controllers;

import com.surveyMonkey.entities.*;
import com.surveyMonkey.repository.SurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AdminController {
    private SurveyRepository surveyRepository;
    @GetMapping({"/home", "/"})
    public String home(Model model){
        return "index";
    }

    @GetMapping({"/surveyTest"})
    public String createSurvey(Model model){
        return "creation";
    }
    @GetMapping({"/surveyQuestions"})
    public String createQuestions(@RequestParam int questions, Model model){
        System.out.println(questions);
        QuestionLabel[] label=new QuestionLabel[questions];
        for(int i=0;i<questions;i++){
            label[i]=new QuestionLabel();
        }
        model.addAttribute("questions",label);
        return "questions";
    }
    @RequestMapping("/viewSurveys")
    public SurveyRepository listSurvey(@ModelAttribute(value = "questions") QuestionLabel[] questions){
        System.out.println(questions);
        Survey survey=new Survey();
        Question q;
        for(QuestionLabel ql:questions){
            switch(ql.getType()){
                case "Histogram": q=new HistoQuestion();
                case "OpenEnded": q=new OpenEndedQuestion();
                case "Option": q=new OptionQuestion();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + ql.getType());
            }
            survey.setQuestion(q);
        }
        surveyRepository.save(survey);
        return surveyRepository;
    }
}
