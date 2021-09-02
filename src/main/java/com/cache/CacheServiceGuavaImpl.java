package com.cache;

import com.google.common.cache.*;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

public class CacheServiceGuavaImpl implements CacheService {
    private static float averageTimePuttingInCache;
    CacheLoader<String, String> loader;
    LoadingCache<String, String> cache;
    RemovalListener<String, String> listener;
    @Getter
    @Setter
    private int evictionCount;

    public CacheServiceGuavaImpl() {
        evictionCount = 0;

        loader = new CacheLoader<>() {
            @Override
            public String load(String key) {
                return key;
            }
        };

        listener = notification -> {
            if (notification.wasEvicted()) {
                setEvictionCount(getEvictionCount() + 1);
                System.out.println(notification.getCause().name()); //TODO: Add logger
            }
        };

        cache = CacheBuilder.newBuilder()
                    .expireAfterAccess(5, TimeUnit.SECONDS)
                    .removalListener(listener)
                    .maximumSize(MAX_SIZE)
                    .build(loader);
    }

    @Override
    public void put(String key, String value, long periodInMillis) {

    }

    @Override
    public void put(String value) {
        long startTime = System.currentTimeMillis();
        cache.getUnchecked(value);
        updateAverageTimePuttingIntoCache(System.currentTimeMillis() - startTime);
    }

    @Override
    public void updateAverageTimePuttingIntoCache(long time) {
        if (averageTimePuttingInCache == 0) {
            averageTimePuttingInCache = time;
        }
        else {
            averageTimePuttingInCache = (averageTimePuttingInCache + time) / 2;
        }
    }

    @Override
    public Object get(String key) {
        return cache.getIfPresent(key);
    }

    @Override
    public long size() {
        return cache.size();
    }

    @Override
    public float getAverageTimePuttingIntoCache() {
        return averageTimePuttingInCache;
    }
}
