package com.course.calypso.concurrency.thread_pool;

import org.junit.Test;

public class ExecuteSingleThreadPoolTest {

    @Test
    public void givenExecutorSingleThreadPoolRunTask() throws InterruptedException {
        ExecuteSingleThreadPool executeNewFixedThreadPool = new ExecuteSingleThreadPool();
        executeNewFixedThreadPool.runTaskInPool();

    }

}
