package com.oracle.db23c.springboot3withcaffeine.service.impl;

import com.oracle.db23c.springboot3withcaffeine.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MainServiceImpl implements MainService {

    public static final String CACHE_NAME = "MainService";

    @Autowired
    CacheManager cacheManager;

    //    @Autowired
//    CacheService cacheService;
//
//    public String getCache(String cacheName, String keyName){
//        return cacheService.getCache(cacheName,keyName);
//    }
    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "#name")
    public String get(String name) {
        try {
            log.info("sleep 3s...");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return "cached value";
    }

    @Override
    public void clearCache(String cacheName, String key) {
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(cacheName);
        assert caffeineCache != null;
        caffeineCache.clear();
    }
}
