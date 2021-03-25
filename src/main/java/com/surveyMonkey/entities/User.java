package com.surveyMonkey.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "survey_user")
public class User
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Survey> surveys;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }
}
