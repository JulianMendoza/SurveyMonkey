package com.surveyMonkey.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long questionId;
    private String question;
    public Question(){

    }
    public Question(String question){
        setQuestion(question);
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return question.equals(question1.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, question);
    }
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

}
