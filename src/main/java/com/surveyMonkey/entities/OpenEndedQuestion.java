package com.surveyMonkey.entities;

import javax.persistence.Entity;

@Entity
public class OpenEndedQuestion extends Question {
	/**
	 * 
	 */
	private static final long serialVersionUID = 355356807141869645L;

	public OpenEndedQuestion() {

	}

	public OpenEndedQuestion(String question) {
		super(question);
	}

	@Override
	public String toString() {
		return "OpenEndedQuestion: " + getQuestion();
	}
}
