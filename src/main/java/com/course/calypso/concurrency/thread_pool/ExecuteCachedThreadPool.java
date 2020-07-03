package com.course.calypso.concurrency.thread_pool;

import com.course.calypso.service.CalculateTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/*
Run this from unit test.
*/
@Slf4j
public class ExecuteCachedThreadPool {
/*
Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when
they are available. These pools will typically improve the performance of programs that execute many short-lived
asynchronous tasks. Calls to execute will reuse previously constructed threads if available. If no existing thread
is available, a new thread will be created and added to the pool. Threads that have not been used for sixty seconds
are terminated and removed from the cache. Thus, a pool that remains idle for long enough will not consume any
resources.
*/
    public void runTaskInPool() throws InterruptedException {

        ExecutorService newCacheThreadPool = Executors.newCachedThreadPool();
        log.info("Start the Cache Thread Pool");
        for (int index = 0; index < 10; index++) {
            Thread.sleep(5000);
            newCacheThreadPool.submit(new CalculateTask());
        }
        log.info("End the Thread Pool");
    }
}
