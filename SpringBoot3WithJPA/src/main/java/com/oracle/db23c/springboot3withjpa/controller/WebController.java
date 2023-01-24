package com.oracle.db23c.springboot3withjpa.controller;

import com.oracle.db23c.springboot3withjpa.entity.NewVideo;
import com.oracle.db23c.springboot3withjpa.vo.UniversalSearch;
import com.oracle.db23c.springboot3withjpa.entity.VideoEntity;
import com.oracle.db23c.springboot3withjpa.service.MainService;
import com.oracle.db23c.springboot3withjpa.vo.VideoSearch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class WebController {

    private final MainService mainService;

    public WebController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("videos", mainService.getVideos());
        return "index";
    }

    @PostMapping("/new-video")
    public String newVideo(@ModelAttribute NewVideo newVideo) {
        mainService.create(newVideo);
        return "redirect:/";
    }

    // @ModelAttribute注解是Spring MVC对传入表单进行反序列化的信号。
    // @Model参数是一种发送信息进行呈现的机制。
    @PostMapping("/multi-field-search")
    public String multiFieldSearch( //
                                    @ModelAttribute VideoSearch search, //
                                    Model model) {
        List<VideoEntity> searchResults = //
                mainService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

    @PostMapping("/universal-search")
    public String universalSearch(@ModelAttribute UniversalSearch search, Model model) {
        List<VideoEntity> searchResults = mainService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }
}
