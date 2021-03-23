package com.surveyMonkey.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long answerId;
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
