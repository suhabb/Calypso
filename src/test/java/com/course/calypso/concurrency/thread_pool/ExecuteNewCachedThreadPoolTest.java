package com.course.calypso.concurrency.thread_pool;

import org.junit.Test;

public class ExecuteNewCachedThreadPoolTest {

    @Test
    public void givenExecutorCacheThreadPoolRunTask() throws InterruptedException {
        ExecuteCachedThreadPool executeNewCachedThreadPoolTest = new ExecuteCachedThreadPool();
        executeNewCachedThreadPoolTest.runTaskInPool();
    }
}
