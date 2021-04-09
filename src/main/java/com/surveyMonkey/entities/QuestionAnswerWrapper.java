package com.surveyMonkey.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Document
public class QuestionAnswerWrapper {

    @Id
    private String qWrapperid;
    @OneToOne(cascade = CascadeType.ALL)
    private Question question;
    @OneToMany(targetEntity = Answer.class, cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public QuestionAnswerWrapper() {
    }

    public QuestionAnswerWrapper(Question q) {
        this.question = q;
    }

    @OneToOne
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @OneToMany(targetEntity = Answer.class)
    public List<Answer> getAnswers() {
        return answers;
    }

    public String getqWrapperid() {
        return qWrapperid;
    }

    public void setqWrapperid(String qWrapperid) {
        this.qWrapperid = qWrapperid;
    }
}

