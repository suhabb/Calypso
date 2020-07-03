package com.course.calypso.concurrency.thread_pool;

import com.course.calypso.service.CalculateTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Run this from unit test ExecuteNewFixedThreadPoolTest.
*/
@Slf4j
public class ExecuteNewFixedThreadPool {
/*
Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue.
At any point, at most nThreads threads will be active processing tasks. If additional tasks are submitted when
all threads are active, they will wait in the queue until a thread is available. If any thread terminates due
to a failure during execution prior to shutdown, a new one will take its place if needed to execute subsequent tasks.
The threads in the pool will exist until it is explicitly shutdown.
*/

    public void runTaskInPool() throws InterruptedException {
        int availableProcessors = Runtime.getRuntime().availableProcessors();// create the treads as per the underlying OS
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(availableProcessors);
        log.info("Start the Thread Pool with {} processors ",availableProcessors);
        for (int index = 0; index < 10; index++) {
            Thread.sleep(5000);
            newFixedThreadPool.submit(new CalculateTask());
        }
        newFixedThreadPool.shutdown();// this will not take addition task in the pool after this call
        log.info("End the Thread Pool");
    }
}
