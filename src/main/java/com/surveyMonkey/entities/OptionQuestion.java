package com.surveyMonkey.entities;
import javax.persistence.*;
@Entity
public class OptionQuestion extends Question {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    public OptionQuestion()
    {
    }
}
