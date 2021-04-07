package com.surveyMonkey.util;

public class StoreAnswerHelper {
    private long questionId;
    private String answer;

    public void setQuestionId(int questionId) {
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
