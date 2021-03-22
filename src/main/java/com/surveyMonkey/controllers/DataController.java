package com.surveyMonkey.controllers;

import com.surveyMonkey.entities.*;
import com.surveyMonkey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public List<Survey> test(){
        List<Survey> survey =new ArrayList<>();
       for(Survey s:surveyRepository.findAll()){
           survey.add(s);
       }
        return survey;
    }
    @GetMapping("/testQuestions")
    public ArrayList<Question> viewQuestions(@RequestParam("title2") String title2){
        for(Survey s:surveyRepository.findAll()){
            if(s.getTitle().equals(title2)) {
                for(Question q:s.getQuestions()){
                    System.out.println(q.getQuestion());
                }
                return s.getQuestions();
            }
        }
        return null;
    }
}
