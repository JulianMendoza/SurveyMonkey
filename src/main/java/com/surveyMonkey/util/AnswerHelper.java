package com.surveyMonkey.util;

import java.util.ArrayList;
import java.util.List;

public class AnswerHelper {
    private String surveyCode;
    private List<StoreAnswerHelper> answeredStored;

    public AnswerHelper() {
        answeredStored = new ArrayList<>();
    }

    public String getSurveyCode() {
        return this.surveyCode;
    }

    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
    }

    public List<StoreAnswerHelper> getAnsweredStored() {
        return this.answeredStored;
    }

    public void setAnsweredStored(List<StoreAnswerHelper> answeredStored) {
        this.answeredStored = answeredStored;
    }

}
