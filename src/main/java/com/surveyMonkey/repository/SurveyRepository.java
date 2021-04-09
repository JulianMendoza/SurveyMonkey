package com.surveyMonkey.repository;

import com.surveyMonkey.entities.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SurveyRepository extends MongoRepository<Survey, String> {
}
