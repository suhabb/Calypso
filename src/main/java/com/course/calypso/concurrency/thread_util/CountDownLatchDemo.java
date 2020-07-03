package com.course.calypso.concurrency.thread_util;

import com.course.calypso.service.FoodProcessorTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CountDownLatchDemo implements Runnable {

    private String[] items;

    private CountDownLatch countDownLatch;

    public CountDownLatchDemo(CountDownLatch countDownLatch, String[] items) {
        this.countDownLatch = countDownLatch;
        this.items = items;
    }

    @Override
    public void run() {
        for (int index = 0; index < items.length; index++) {
            log.info("Count down latch before processing :{}", this.countDownLatch.getCount());
            try {
                countDownLatch.countDown();// wait til the countdown reached 0
                log.info("Process order item :{}", items[index]);
                Thread.sleep(2000);
                log.info("Count down latch after processing :{}", this.countDownLatch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
