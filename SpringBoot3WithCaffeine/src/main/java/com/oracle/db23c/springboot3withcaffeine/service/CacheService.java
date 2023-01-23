package com.oracle.db23c.springboot3withcaffeine.service;

public interface CacheService {

    String getCache(String cacheName, String keyName) throws InterruptedException;

}
