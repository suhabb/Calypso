package com.course.calypso.concurrency.thread_pool;

import org.junit.Test;

public class ExecuteNewFixedThreadPoolTest {

    @Test
    public void givenExecutorThreadPoolRunTask() throws InterruptedException {
        ExecuteNewFixedThreadPool executeNewFixedThreadPool = new ExecuteNewFixedThreadPool();
        executeNewFixedThreadPool.runTaskInPool();

    }
}
