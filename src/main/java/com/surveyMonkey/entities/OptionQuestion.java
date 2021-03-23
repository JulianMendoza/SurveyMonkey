package com.surveyMonkey.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class OptionQuestion extends Question {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1795113345322280778L;
	@ElementCollection
	private List<String> options = new ArrayList<>();

	public OptionQuestion() {
	}

	public OptionQuestion(String question, List<String> options) {
		super(question);
		setOptions(options);
	}

	public OptionQuestion(List<String> lst) {
		setOptions(lst);
	}

	@ElementCollection
	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public void addOption(String option) {
		options.add(option);
	}

	@Override
	public String toString() {
		return "OptionQuestion: " + getQuestion() + " options: " + Arrays.asList(options);
	}
}
