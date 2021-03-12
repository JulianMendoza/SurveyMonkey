package com.surveyMonkey.entities;

import javax.persistence.*;
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    public Answer(){
    }
}
