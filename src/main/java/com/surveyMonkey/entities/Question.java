package com.surveyMonkey.entities;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public class Question {
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String question;
    public Question(){

    }
    public Question(String question){
        setQuestion(question);
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    @Id
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return id.equals(question1.id) && question.equals(question1.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question);
    }
}
