package com.SurveyMonkey.Entities;

import javax.persistence.*;
@Entity
public class User
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
}
