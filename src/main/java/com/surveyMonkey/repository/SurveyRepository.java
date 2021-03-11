package com.surveyMonkey.repository;

import com.surveyMonkey.entities.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
}
