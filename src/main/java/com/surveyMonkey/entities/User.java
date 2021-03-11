package com.surveyMonkey.entities;

import javax.persistence.*;
@Entity
public class User
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public User(){

    }
}
