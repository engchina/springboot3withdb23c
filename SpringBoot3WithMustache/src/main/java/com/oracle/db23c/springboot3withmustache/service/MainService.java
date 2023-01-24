package com.oracle.db23c.springboot3withmustache.service;

import com.oracle.db23c.springboot3withmustache.entity.Video;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    private List<Video> videoList = List.of(
            new Video("video1"),
            new Video("video2"),
            new Video("video3")
    );

    public List<Video> getVideoList() {
        return videoList;
    }

    public Video create(Video newVideo) {
        List<Video> extend = new ArrayList<>(videoList);
        extend.add(newVideo);
        this.videoList = List.copyOf(extend);
        return newVideo;
    }
}
