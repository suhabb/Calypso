package com.course.calypso.concurrency.thread_pool;

import com.course.calypso.concurrency.handler.RejectionCustomHandler;
import com.course.calypso.service.CalculateTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ExecuteThreadPool {

    public void runTaskInPool() throws InterruptedException {

        //RejectedExecutionHandler implementation
        RejectionCustomHandler rejectionCustomHandler = new RejectionCustomHandler();

        //Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();


        /* The thread pool contained inside the ThreadPoolExecutor can contain a varying amount of threads.
            The number of threads in the pool is determined by these variables:
            - corePoolSize
            - maximumPoolSize
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                5,
                0L,// Not Applicable - since thread size are fixed , so no thread dies
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),// create a que to hold the task
                threadFactory,
                rejectionCustomHandler);//Thread pool executor with rejection handler

        for (int index = 0; index < 10; index++) {

            threadPoolExecutor.submit(new CalculateTask());
            Thread.sleep(2000);
        }
        Thread.sleep(3000);

        //initiate shutdown- will shutdown only after executing all task in the q, and will take any more ant task
        //threadPoolExecutor.shutdown();
        log.info("End the Thread Pool");
    }
}
