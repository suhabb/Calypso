package com.course.calypso.concurrency.locks;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ReentrantLockWithConditionDemoTest {

    @Test
    public void testReentrantLockWithCondition() throws InterruptedException {

        ReentrantLockWithConditionDemo reentrantLockWithConditionDemo = new ReentrantLockWithConditionDemo();

        new Thread(() -> {
            try {
                reentrantLockWithConditionDemo.pushToStack("John Doe");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        Thread.sleep(2000);

        new Thread(() -> {
            try {
                reentrantLockWithConditionDemo.pushToStack("Smith");
                log.info("Thread Name : {},Push to stack : {}", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(2000);
        new Thread(() -> {
            try {
                log.info("Thread Name : {},Pop from stack : {}", Thread.currentThread().getName(),
                        reentrantLockWithConditionDemo.popFromStack());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(2000);

        new Thread(() -> {
            try {
                reentrantLockWithConditionDemo.pushToStack("Neo");
                log.info("Thread Name : {},Push to stack ", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
