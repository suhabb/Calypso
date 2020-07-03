package com.course.calypso.concurrency.locks;


import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
class ReentrantLockDemo implements Runnable {

    String name;
    ReentrantLock reentrantLock;

    public ReentrantLockDemo(ReentrantLock reentrantLock, String name) {
        this.reentrantLock = reentrantLock;
        this.name = name;
    }

    public void run() {
        boolean done = false;
        while (!done) {
            //Getting Outer Lock
            boolean ans = reentrantLock.tryLock();

            // Returns True if lock is free
            if (ans) {
                try {
                    Date date = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    log.info("task name - " + name + " outer lock acquired at " + ft.format(date) + " Doing outer work");
                    Thread.sleep(1500);

                    // Getting Inner Lock
                    reentrantLock.lock();
                    try {
                        date = new Date();
                        ft = new SimpleDateFormat("hh:mm:ss");
                       log.info("task name - " + name
                                + " inner lock acquired at : "
                                + ft.format(date)
                                + " Doing inner work");
                        log.info("Lock Hold Count -{} ", reentrantLock.getHoldCount());
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //Inner lock release
                        log.info("task name - " + name +
                                " releasing inner lock");

                        reentrantLock.unlock();
                    }
                    log.info("Lock Hold Count - {}" ,reentrantLock.getHoldCount());
                    log.info("task name - {} work done",name);

                    done = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //Outer lock release
                   log.info("task name - " + name + " releasing outer lock");

                    reentrantLock.unlock();
                    log.info("Lock Hold Count - " +
                            reentrantLock.getHoldCount());
                }
            } else {
                log.info("task name - " + name + " waiting for lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}