package com.surveyMonkey.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class QuestionAnswerWrapper {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long qWrapperid;
    @OneToOne(cascade = CascadeType.ALL)
    private Question question;
    @OneToMany(targetEntity=Answer.class,cascade = CascadeType.ALL)
    private List<Answer> answers=new ArrayList<>();

    public QuestionAnswerWrapper() {
    }
    public QuestionAnswerWrapper(Question q){
        this.question=q;
    }
    @OneToOne
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    @OneToMany(targetEntity=Answer.class)
    public List<Answer> getAnswers() {
        return answers;
    }
    public Long getqWrapperid() {
        return qWrapperid;
    }

    public void setqWrapperid(Long qWrapperid) {
        this.qWrapperid = qWrapperid;
    }
}
