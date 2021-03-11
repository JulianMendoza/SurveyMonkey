package com.SurveyMonkey.Controllers;

import com.SurveyMonkey.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;
}
