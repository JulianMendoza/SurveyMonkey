package com.surveyMonkey.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Survey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7426934374543805936L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL)
	private List<QuestionAnswerWrapper> survey = new ArrayList<>();
	private String title;
	private String surveyCode;
	private String surveyPassword;

	public Survey() {
	}
	public Survey(String title, String password) {
		this.title = title;
		this.surveyPassword = password;
	}

	public void setQuestion(Question q) {
		survey.add(new QuestionAnswerWrapper(q));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSurveyCode() {
		return surveyCode;
	}

	public List<QuestionAnswerWrapper> getSurvey() {
		return survey;
	}

	public void setSurvey(List<QuestionAnswerWrapper> survey) {
		this.survey = survey;
	}

	public void setSurveyCode(String surveyCode) {
		this.surveyCode = surveyCode;
	}

	public String getSurveyPassword() {
		return surveyPassword;
	}

	public void setSurveyPassword(String surveyPassword) {
		this.surveyPassword = surveyPassword;
	}
}
