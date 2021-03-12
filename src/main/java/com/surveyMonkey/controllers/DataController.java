package com.surveyMonkey.controllers;

import com.surveyMonkey.entities.*;
import com.surveyMonkey.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
