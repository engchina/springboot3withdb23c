package com.oracle.db23c.controller;

import com.oracle.db23c.bo.User;
import com.oracle.db23c.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/main")
public class MainController {

    @Resource
    UserMapper userMapper;

    @GetMapping
    public String sayHello(Principal principal) {
        User dbuser = userMapper.findByUsername(principal.getName());
        return "Hello " + dbuser.getUsername();
    }

    @GetMapping("/index")
    public String goIndex(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        User dbuser = userMapper.findByUsername(user.getUsername());
        return "Hello " + dbuser.getUsername();
    }

    @GetMapping("/admin")
    public String goAdmin(@AuthenticationPrincipal User user) {
        User dbuser = userMapper.findByUsername(user.getUsername());
        return "Hello " + user.getUsername();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public String goDelete() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        User dbuser = userMapper.findByUsername(user.getUsername());
        return "Hello " + dbuser.getUsername();
    }
}
