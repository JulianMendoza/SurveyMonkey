package com.surveyMonkey.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document
public class Answer implements Serializable {

    private static final long serialVersionUID = -6024463549612035738L;
    @Id
    private String answerId;
    private String answer;

    public Answer() {
    }

    public Answer(String s) {
        this.answer = s;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
