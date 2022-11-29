package com.oracle.db23c.controller;

import com.oracle.db23c.dto.RegistrationForm;
import com.oracle.db23c.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    UserMapper userMapper;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userMapper.saveUser(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
