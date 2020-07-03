package com.course.calypso.concurrency.locks;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class StampedLockDemoTest {

    @Test
    public void testStampedLock() throws InterruptedException {

        StampedLockDemo stampedLockDemo = new StampedLockDemo();

        new Thread(() -> {
            stampedLockDemo.put("1", "John Doe");
            log.info("Thread Name : {},Reading from map : {}", Thread.currentThread().getName(),
                    stampedLockDemo.get("1"));
        }).start();

        Thread.sleep(2000);

        new Thread(() -> {
            stampedLockDemo.put("2", "Jane Doe");
            log.info("Thread Name : {},Reading from map : {}", Thread.currentThread().getName(),
                    stampedLockDemo.get("2"));
        }).start();
    }


}
