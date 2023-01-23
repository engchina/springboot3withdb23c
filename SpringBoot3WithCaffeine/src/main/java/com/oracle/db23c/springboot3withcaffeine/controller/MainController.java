package com.oracle.db23c.springboot3withcaffeine.controller;

import com.oracle.db23c.springboot3withcaffeine.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping("/get")
    public String get(){
        return mainService.get("key1");
    }

    @RequestMapping("/clear")
    public void clear(){
        mainService.clearCache("MainService", "key1");
    }
}
