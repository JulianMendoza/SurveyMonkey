package com.surveyMonkey.repository;

import com.surveyMonkey.entities.Survey;
import org.springframework.data.repository.CrudRepository;

public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Survey findById(long id);
}
