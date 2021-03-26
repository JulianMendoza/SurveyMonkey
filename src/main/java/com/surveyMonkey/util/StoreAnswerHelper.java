package com.surveyMonkey.util;

import java.util.List;

public class StoreAnswerHelper {
    private int questionId;
    private String answer;

    public void setQuestionId(int questionId){
        this.questionId = questionId;
    }

    public int getQuestionId(){
        return questionId;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String getAnswer(){
        return answer;
    }


}
