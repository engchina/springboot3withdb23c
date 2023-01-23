package com.oracle.db23c.springboot3withcaffeine.service;

public interface MainService {

    String get(String key);
    void clearCache(String cacheName, String key);
}
