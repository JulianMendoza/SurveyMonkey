package com.surveyMonkey.util;

import java.util.List;

public class SurveyHelper {
    private String title;
    private String password;
    private List<QuestionHelper> questions;
    public SurveyHelper() {
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<QuestionHelper> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionHelper> questions) {
        this.questions = questions;
    }


}
