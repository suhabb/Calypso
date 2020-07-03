package com.course.calypso.concurrency.thread_util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchDemoTest {

    @Test
    public void testCountDownLatch() throws InterruptedException {


        String [] items= {"salad","salami","pizza"};
        CountDownLatch countDownLatch = new CountDownLatch(items.length);

        CountDownLatchDemo itemOrder = new CountDownLatchDemo(countDownLatch, items);
        new Thread(itemOrder).start();
        countDownLatch.await();
        log.info("Completed");

    }

    @Test
    public void testCountDownLatchExecutorService() throws InterruptedException {


        String [] items= {"salad","salami","pizza"};
        //This count is essentially the number of threads, for which latch should wait.
        // This value can be set only once,
        // and CountDownLatch provides no other mechanism to reset this count.
        CountDownLatch countDownLatch = new CountDownLatch(items.length);

        CountDownLatchDemo itemOrder = new CountDownLatchDemo(countDownLatch, items);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(itemOrder);
        countDownLatch.await();// this will wait till all the three task are complete and return when count down is 0
        log.info("Completed");

    }
}
