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
    @GetMapping({"/"})
    public String home(Model model){

        return "index";
    }
    @GetMapping({"/surveyTest","/home"})
    public String createSurvey(){

        return "creation";
    }
    @GetMapping({"/surveyQuestions"})
    public String createQuestions(@RequestParam("title") String title ,Model model){
        Survey s=new Survey(title);
        surveyRepository.save(s);
        model.addAttribute("title",title);
        model.addAttribute("addQuestion",new QuestionLabel());
        return "questions";
    }
    @PostMapping("/survey")
    public String listSurvey(@RequestParam("title") String title, @ModelAttribute QuestionLabel label,Model model) {
        System.out.println("LABEL: "+label.getType());
        if (label.getType() !=null){
            Survey survey = new Survey();
            Question q;
            switch (label.getType()) {
                case "Histogram":
                    q = new HistoQuestion();
                case "Open Ended":
                    q = new OpenEndedQuestion();
                case "Option":
                    q = new OptionQuestion();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + label.getType());
            }
            for(Survey s: surveyRepository.findAll()){
                if(s.getTitle().equals(title)) {
                    survey=s;
                    break;
                }
            }
            survey.setQuestion(q);
            surveyRepository.save(survey);

        }
            model.addAttribute("addQuestion",new QuestionLabel());
            return "questions";
    }

    @PostMapping({"/surveyResults"})
    public String surveyResult(){

            return "results";
    }

}
