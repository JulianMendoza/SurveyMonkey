package com.surveyMonkey.repository;

import com.surveyMonkey.entities.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question,Long> {
}
