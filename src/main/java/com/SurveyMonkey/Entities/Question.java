package com.SurveyMonkey.Entities;
import javax.persistence.*;
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
}
