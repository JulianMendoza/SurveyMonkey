package com.surveyMonkey.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

import static com.surveyMonkey.util.Constants.HISTOGRAM;

@Document
public class HistoQuestion extends Question {

    private static final long serialVersionUID = 5551750221995506502L;
    private int minVal;
    private int maxVal;
    private int stepSize;
	public HistoQuestion() {

	}
    public HistoQuestion(String question, int minVal, int maxVal, int stepSize) {
        super(question,HISTOGRAM);
        setMinVal(minVal);
        setMaxVal(maxVal);
        setStepSize(stepSize);
    }

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

    @Override
    public String toString() {
        return "HistoQuestion: " + getQuestion() + " minval: " + getMinVal() + " maxVal: " + getMaxVal() + " stepSize: " + getStepSize();
    }

    public static void main(String[] args) {
        HistoQuestion q = new HistoQuestion("dsadasda", 4, 5, 2);
        System.out.println(q.getClass().getSimpleName());
    }
}


