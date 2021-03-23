package com.surveyMonkey.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade=CascadeType.ALL)
    private List<QuestionAnswerWrapper> survey=new ArrayList<>();
    private String title;
    private String surveyCode;
    private String surveyPassword;
    public Survey() {
        this("","");
    }
    public Survey(String title){
        this(title,"");
    }
    public Survey(String title,String password){
        this.title=title;
        this.surveyPassword=password;
    }
    public void setQuestion(Question q){
        survey.add(new QuestionAnswerWrapper(q));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public List<QuestionAnswerWrapper> getSurvey(){
        return survey;
    }
    public void setSurvey(List<QuestionAnswerWrapper> survey){
        this.survey=survey;
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
