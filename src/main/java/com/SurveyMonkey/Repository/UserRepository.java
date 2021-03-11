package com.SurveyMonkey.Repository;

import com.SurveyMonkey.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
