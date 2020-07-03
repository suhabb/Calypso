package com.course.calypso.concurrency.thread_pool;

import com.course.calypso.service.CalculateTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/*
Run this from unit test ExecuteSingleThreadPoolTest.
*/
@Slf4j
public class ExecuteSingleThreadPool {
/*
A single thread pool can be obtained by calling the static newSingleThreadExecutor() method of Executors class.
Where newSingleThreadExecutor method creates an executor that executes a single task at a time.
*/

    public void runTaskInPool() throws InterruptedException {
        ExecutorService newFixedThreadPool = Executors.newSingleThreadExecutor();
        for (int index = 0; index < 10; index++) {

            newFixedThreadPool.submit(new CalculateTask());
            Thread.sleep(2000);
        }

        log.info("End the Thread Pool");
    }
}
