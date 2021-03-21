package com.surveyMonkey.entities;

import javax.persistence.Entity;


@Entity
public class HistoQuestion extends Question {
    private int minVal;
    private int maxVal;

    public int getMinVal() {
        return minVal;
    }

    public void setMinVal(int minVal) {
        this.minVal = minVal;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }

    public int getStepSize() {
        return stepSize;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    private int stepSize;
    public HistoQuestion() {

    }
    @Override
    public String toString(){
        return "hello world";
    }
}
