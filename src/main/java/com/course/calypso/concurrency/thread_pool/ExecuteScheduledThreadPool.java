package com.course.calypso.concurrency.thread_pool;

import com.course.calypso.service.CalculateTask;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
Run this from unit test.
*/
@Slf4j
public class ExecuteScheduledThreadPool {
/*
*A ThreadPoolExecutor that can additionally schedule commands to run after a given delay, or to execute periodically.
*  This class is preferable to Timer when multiple worker threads are needed, or when the additional flexibility or
* capabilities of ThreadPoolExecutor (which this class extends) are required.
Delayed tasks execute no sooner than they are enabled, but without any real-time guarantees about when, after they
* are enabled, they will commence. Tasks scheduled for exactly the same execution time are enabled in first-in-first-out
* (FIFO) order of submission.
* */
    public void runTaskInPool() throws InterruptedException {

        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(10);
        log.info("Start the Scheduled Thread Pool");

        //task to run after 3 seconds delay
        newScheduledThreadPool.schedule(new CalculateTask(), 3, TimeUnit.SECONDS);

        //task to run repeatedly every 4 seconds
        newScheduledThreadPool.scheduleAtFixedRate(new CalculateTask(),2,4,TimeUnit.SECONDS);

        //task to run repeatedly every 4 seconds after the previous task is complete
        newScheduledThreadPool.scheduleWithFixedDelay(new CalculateTask(),3,4,TimeUnit.SECONDS);

        Thread.sleep(19000);
        log.info("End the Thread Pool");
    }
}
