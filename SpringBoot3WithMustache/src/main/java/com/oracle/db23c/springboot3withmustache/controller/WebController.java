package com.oracle.db23c.springboot3withmustache.controller;

import com.oracle.db23c.springboot3withmustache.entity.Video;
import com.oracle.db23c.springboot3withmustache.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WebController {

    private final MainService mainService;

    public WebController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("videoList", mainService.getVideoList());
        return "index";
    }

    @PostMapping("/new-video")
    public String newVideo(@ModelAttribute Video newVideo) {
        mainService.create(newVideo);
        return "redirect:/";
    }

    @GetMapping("/react")
    public String react() {
        return "react";
    }
}
