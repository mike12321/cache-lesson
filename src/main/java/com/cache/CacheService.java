package com.cache;

public interface CacheService {
    int MAX_SIZE = 100000;
    int REMOVAL_TIMEOUT = 5;

    void put(String key, String value, long periodInMillis);

    void put(String value);

    Object get(String key);

    long size();

    float getAverageTimePuttingIntoCache();

    default void printStats() {
        System.out.println("Cache size: " + size());
        System.out.println("Average Time Putting Into Cache " + getAverageTimePuttingIntoCache());
    }

    void updateAverageTimePuttingIntoCache(long time);
}