package com.course.calypso.concurrency.locks;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemoTest {

    @Test
    public void testReentrantLock() throws InterruptedException {
        ReentrantLock rel = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Runnable w1 = new ReentrantLockDemo(rel, "Job1");
        Runnable w2 = new ReentrantLockDemo(rel, "Job2");
        Runnable w3 = new ReentrantLockDemo(rel, "Job3");
        Runnable w4 = new ReentrantLockDemo(rel, "Job4");
        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);
        Thread.sleep(9000);
        pool.shutdown();

    }
}
