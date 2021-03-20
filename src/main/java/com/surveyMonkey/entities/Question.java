package com.surveyMonkey.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Question {
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    public Question(){

    }
    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
