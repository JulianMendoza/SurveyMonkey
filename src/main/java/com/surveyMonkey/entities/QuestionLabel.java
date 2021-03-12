package com.surveyMonkey.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionLabel {
    private String question;
    private String type;
    private String options;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public QuestionLabel(){

    }
    public QuestionLabel(long id){
        this.id=id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
