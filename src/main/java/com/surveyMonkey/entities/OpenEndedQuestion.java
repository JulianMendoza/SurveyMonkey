package com.surveyMonkey.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

import static com.surveyMonkey.util.Constants.OPEN_ENDED;

@Document
public class OpenEndedQuestion extends Question {

    private static final long serialVersionUID = 355356807141869645L;

    public OpenEndedQuestion() {
    }

    public OpenEndedQuestion(String question) {
        super(question,OPEN_ENDED);
    }
    @Override
    public String toString() {
        return "OpenEndedQuestion: " + getQuestion();
    }
}
