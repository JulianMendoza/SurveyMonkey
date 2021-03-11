package com.SurveyMonkey.Controllers;

import com.SurveyMonkey.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping({"/home", "/"})
    public String home(Model model){
        return "index";
    }
}
