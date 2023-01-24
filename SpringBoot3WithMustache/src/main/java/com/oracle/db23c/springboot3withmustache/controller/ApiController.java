package com.oracle.db23c.springboot3withmustache.controller;

import com.oracle.db23c.springboot3withmustache.entity.Video;
import com.oracle.db23c.springboot3withmustache.service.MainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private final MainService mainService;

    public ApiController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/api/videos")
    public List<Video> all() {
        return mainService.getVideoList();
    }

    @PostMapping("/api/videos")
    public Video newVideo(@RequestBody Video newVideo) {
        return mainService.create(newVideo);
    }
}
