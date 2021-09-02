package com.cache;

import lombok.Getter;
import lombok.Setter;

import java.lang.ref.SoftReference;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CacheServiceImpl implements CacheService {
    private static float averageTimePuttingInCache;

    private final ConcurrentHashMap<String, SoftReference<CacheEntry>> cache = new ConcurrentHashMap<>();

    public CacheServiceImpl() {
        Thread cleanUpThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(REMOVAL_TIMEOUT * 1000);
                    cache.entrySet().removeIf(entry -> Optional.ofNullable(entry.getValue())
                            .map(SoftReference::get).map(CacheEntry::isExpired).orElse(false));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        cleanUpThread.setDaemon(true);
        cleanUpThread.start();
        averageTimePuttingInCache = 0F;
    }

    public void put(String key, String value, long periodInMillis) {
        if (key == null) {
            return;
        }
        long startTime = System.currentTimeMillis();

        if (cache.size() >= MAX_SIZE) {
            cleanUpUnusedEntries();
        }

        long expiryTime = System.currentTimeMillis() + periodInMillis;
        cache.put(key, new SoftReference<>(new CacheEntry(value, expiryTime)));
        updateAverageTimePuttingIntoCache(System.currentTimeMillis() - startTime);
    }

    @Override
    public void put(String value) {

    }


    @Override
    public String get(String key) {
        CacheEntry cacheEntry = cache.get(key).get();

        if (cacheEntry != null) {
            cacheEntry.setFrequency(cacheEntry.getFrequency() + 1);
            return cacheEntry.getValue();
        }

        return null;
    }

    @Override
    public long size() {
        return cache.entrySet().stream().filter(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get).map(cacheObject -> !cacheObject.isExpired()).orElse(false)).count();
    }

    @Override
    public float getAverageTimePuttingIntoCache() {
        return averageTimePuttingInCache;
    }

    private void cleanUpUnusedEntries() {
        cache.entrySet().removeIf(entry -> entry.getValue().get().getFrequency() == 0);
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

    private static class CacheEntry {

        @Getter
        private String value;
        private long expirationTime;
        @Getter
        @Setter
        private int frequency;

        public CacheEntry(String value, long expirationTime) {
            this.value = value;
            this.expirationTime = expirationTime;
            this.frequency = 0;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }
}