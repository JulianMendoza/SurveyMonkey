package com.surveyMonkey.util;

import java.util.List;

public class StoreAnswerHelper {
    private long questionId;
    private String answer;

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

}
