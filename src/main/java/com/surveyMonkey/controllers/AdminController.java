package com.surveyMonkey.controllers;

import com.surveyMonkey.entities.*;
import com.surveyMonkey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private SurveyRepository surveyRepository;
    @GetMapping({"/home"})
    public String home(Model model){
        return "index";
    }
    @GetMapping({"/surveyTest","/"})
    public String createSurvey(){
        return "creation";
    }
    @GetMapping({"/surveyQuestions"})
    public String createQuestions(Model model){
        model.addAttribute("addQuestion",new QuestionLabel());
        return "questions";
    }
    @PostMapping("/survey")
    public String listSurvey(@ModelAttribute QuestionLabel label,Model model) {
        if (label.getType() !=null){
            System.out.println(label.getType());
            Survey survey = new Survey();
            Question q;
            switch (label.getType()) {
                case "Histogram":
                    q = new HistoQuestion();
                case "OpenEnded":
                    q = new OpenEndedQuestion();
                case "Option":
                    q = new OptionQuestion();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + label.getType());
            }
            survey.setQuestion(q);
            surveyRepository.save(survey);

        }
            model.addAttribute("addQuestion",new QuestionLabel());
            return "questions";
    }
}
