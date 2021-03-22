package com.surveyMonkey.entities;

import javax.persistence.Entity;


@Entity
public class OpenEndedQuestion extends Question {
    public OpenEndedQuestion(){
        
    }
    public OpenEndedQuestion(String question){
        super(question);
    }
    @Override
    public String toString(){
        return "OpenEndedQuestion: "+getQuestion();
    }
}
