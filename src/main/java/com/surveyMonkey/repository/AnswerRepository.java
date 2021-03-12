package com.surveyMonkey.repository;

import com.surveyMonkey.entities.Answer;
import org.springframework.data.repository.CrudRepository;


public interface AnswerRepository extends CrudRepository<Answer,Long> {
}
