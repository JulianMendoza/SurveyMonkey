package com.surveyMonkey.repository;

import com.surveyMonkey.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long> {
}
