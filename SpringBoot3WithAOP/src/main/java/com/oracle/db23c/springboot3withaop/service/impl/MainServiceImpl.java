package com.oracle.db23c.springboot3withaop.service.impl;

import com.oracle.db23c.springboot3withaop.aspect.AroundAnnotation;
import com.oracle.db23c.springboot3withaop.service.MainService;
import org.springframework.stereotype.Service;

@Service
@AroundAnnotation
public class MainServiceImpl implements MainService {
    @Override
    public void invokeMe() {
        System.err.println("inside method invokeMe()");
    }
}
