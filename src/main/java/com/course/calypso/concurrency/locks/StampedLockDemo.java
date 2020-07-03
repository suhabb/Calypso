package com.course.calypso.concurrency.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

@Slf4j
public class StampedLockDemo {

    Map<String, String> map = new HashMap<>();
    private StampedLock lock = new StampedLock();

    public void put(String key, String value) {

        long stamp = lock.writeLock();
        log.info("Write Lock Stamp  {}:",stamp);
        try {
            map.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public String get(String key)  {
        long stamp = lock.readLock();
        log.info("Read Lock Stamp :{}",stamp);
        try {
            return map.get(key);
        } finally {
            lock.unlockRead(stamp);
        }
    }
}