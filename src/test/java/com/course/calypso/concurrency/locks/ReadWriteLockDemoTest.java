package com.course.calypso.concurrency.locks;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ReadWriteLockDemoTest {

    @Test
    public void testReadWriteLockHashMap() {

        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        new Thread(() -> {
            readWriteLockDemo.put("1", "John Doe");
            log.info("Thread Name : {},Reading from map : {}", Thread.currentThread().getName(),
                    readWriteLockDemo.get("1"));
        }).start();

        new Thread(() -> {
            readWriteLockDemo.put("2", "Jane Doe");
            log.info("Thread Name : {},Reading from map : {}", Thread.currentThread().getName(),
                    readWriteLockDemo.get("2"));
        }).start();

        new Thread(() -> {
            readWriteLockDemo.put("3", "Smith");
            log.info("Thread Name : {},Reading from map : {}", Thread.currentThread().getName()
                    , readWriteLockDemo.containsKey("3"));
        }).start();

        new Thread(() -> {
            readWriteLockDemo.remove("2");
            log.info("Thread Name:{},Reading from map : {}", Thread.currentThread().getName(),
                    readWriteLockDemo.containsKey("2"));
        }).start();
    }
}
