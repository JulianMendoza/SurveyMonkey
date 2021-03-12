package com.surveyMonkey.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HistoQuestion extends Question {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    public HistoQuestion(){

    }
}
