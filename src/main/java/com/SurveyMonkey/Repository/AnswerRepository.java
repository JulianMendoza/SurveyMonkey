package com.SurveyMonkey.Repository;

import com.SurveyMonkey.Entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long>{
}
