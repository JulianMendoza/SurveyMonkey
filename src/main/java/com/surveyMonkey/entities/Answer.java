package com.surveyMonkey.entities;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String answer;
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Answer(){
    }
}
