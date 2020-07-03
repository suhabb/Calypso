package com.course.calypso.concurrency.locks;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
class PrintDemo {

    private final Lock queueLock = new ReentrantLock();
    public void print() {
        queueLock.lock();//lock enforced

        try {
            Long duration = (long) (Math.random() * 10000);
            log.info("Thread Name: {},   Time Taken :{} seconds", Thread.currentThread().getName(), (duration / 1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.info("{} printed the document successfully.\n", Thread.currentThread().getName());
            queueLock.unlock(); // lock released
        }
    }
}

@Slf4j
class ThreadDemo extends Thread {

    PrintDemo printDemo;

    ThreadDemo(String name, PrintDemo printDemo) {
        super(name);
        this.printDemo = printDemo;
    }

    @Override
    public void run() {
        log.info("{} starts printing a document\n", Thread.currentThread().getName());
        printDemo.print();
    }
}

public class LockDemo {

    public static void main(String args[]) {
        PrintDemo PD = new PrintDemo();

        ThreadDemo t1 = new ThreadDemo("Thread - 1 ", PD);
        ThreadDemo t2 = new ThreadDemo("Thread - 2 ", PD);
        ThreadDemo t3 = new ThreadDemo("Thread - 3 ", PD);
        ThreadDemo t4 = new ThreadDemo("Thread - 4 ", PD);

        //run multiple threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}