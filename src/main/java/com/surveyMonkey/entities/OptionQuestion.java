package com.surveyMonkey.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class OptionQuestion extends Question {
    @ElementCollection
    private List<String> options=new ArrayList<>();
    public OptionQuestion()
    {
    }
    @ElementCollection
    public List<String> getOptions(){
        return options;
    }
    public void setOptions(List<String> options){
        this.options=options;
    }
    public void addOption(String option){
        options.add(option);
    }
}
