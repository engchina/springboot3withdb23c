package com.oracle.db23c.springboot3withaop.controller;

import com.oracle.db23c.springboot3withaop.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping("/")
    public String sayHello(){
        mainService.invokeMe();
        return "Hello World!";
    }
}
