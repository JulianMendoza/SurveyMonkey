package com.surveyMonkey.entities;

import javax.persistence.*;

@MappedSuperclass
public class Question {
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String question;
    public Question(){

    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    @Id
    public Long getId() {
        return id;
    }
}
