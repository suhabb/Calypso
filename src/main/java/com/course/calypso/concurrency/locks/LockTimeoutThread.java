package com.course.calypso.concurrency.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
class LockTimeOutDemo {

    private final Lock lock = new ReentrantLock();

    public void doWorkWithTheDatabase(int timeout)
            throws InterruptedException {

        if (lock.tryLock(timeout, TimeUnit.SECONDS)) {
            try {
                log.info("Updated database");
                Thread.sleep(20000);
            } finally {
                log.info("Unlock the object");
                lock.unlock();
            }
        } else {
            throw new RuntimeException("Could not acquire database lock");
        }
    }
}

@Slf4j
public class LockTimeoutThread implements Runnable {

    LockTimeOutDemo lockTimeOutDemo = new LockTimeOutDemo();

    private int timeout;

    public LockTimeoutThread(int timeout){
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            log.info("Thread Name:{} ",Thread.currentThread().getName());
            lockTimeOutDemo.doWorkWithTheDatabase(timeout);
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
