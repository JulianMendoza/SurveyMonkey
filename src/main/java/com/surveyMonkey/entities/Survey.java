package com.surveyMonkey.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Transient
    private HashMap <Question, List<Answer>> survey;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Answer> answers;
    private String title;
    private String surveyCode;
    private String surveyPassword;
    public Survey(){
        this("");
    }
    public Survey(String title){
        this.title=title;
        answers = new ArrayList<>();
        survey = new HashMap<>();
    }
    public void setQuestion(Question q){
        survey.put(q,new ArrayList<>());
    }
    public void removeQuestion(){
    }
    public void setAnswer(Question q,Answer a){
        answers.add(a);
        survey.put(q,answers);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<Question, List<Answer>> getSurvey() {
        return survey;
    }

    public void setSurvey(HashMap<Question, List<Answer>> survey) {
        this.survey = survey;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSurveyCode() {
        return surveyCode;
    }

    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
    }

    public String getSurveyPassword() {
        return surveyPassword;
    }

    public void setSurveyPassword(String surveyPassword) {
        this.surveyPassword = surveyPassword;
    }
}
