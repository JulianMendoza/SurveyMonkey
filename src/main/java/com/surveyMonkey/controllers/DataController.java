package com.surveyMonkey.controllers;

import com.surveyMonkey.entities.HistoQuestion;
import com.surveyMonkey.entities.OpenEndedQuestion;
import com.surveyMonkey.entities.OptionQuestion;
import com.surveyMonkey.entities.Survey;
import com.surveyMonkey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DataController {
    @Autowired
    private SurveyRepository surveyRepository;
    @GetMapping("/data")
    public Survey showData(@RequestParam("title2") String title2){
        for(Survey s:surveyRepository.findAll()){
            if(s.getTitle().equals(title2)) {
                return s;
            }
        }
        return null;
    }
    @GetMapping("/test")
    public Survey test(@RequestParam("title2") String title2){
        Survey s = new Survey(title2);
        OptionQuestion q=new OptionQuestion();
        q.setQuestion("This is test question 1. It is an option question");
        ArrayList<String> options=new ArrayList<>();
        q.addOption("Test Option label1");
        q.addOption("Test Option label2");
        q.setOptions(options);
        s.setQuestion(q);
        q=new OptionQuestion();
        q.setQuestion("This is test question 1.1. It is an option question");
        options=new ArrayList<>();
        q.addOption("Test Option label1");
        q.addOption("Test Option label2");
        q.setOptions(options);
        HistoQuestion q2=new HistoQuestion();
        q2.setMaxVal(3);
        q2.setMinVal(1);
        q2.setStepSize(1);
        q2.setQuestion("This is test question 2. It is a histo question");
        s.setQuestion(q2);
        OpenEndedQuestion q3=new OpenEndedQuestion();
        q3.setQuestion("This is test question 3. It is an open ended question");
        s.setQuestion(q3);
        surveyRepository.save(s);
        for(Survey s1:surveyRepository.findAll()){
            if(s1.getTitle().equals(title2)) {
                return s1;
            }
        }
        return null;
    }

}
