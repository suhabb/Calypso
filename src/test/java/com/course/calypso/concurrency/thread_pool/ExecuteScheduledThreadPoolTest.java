package com.course.calypso.concurrency.thread_pool;

import org.junit.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ExecuteScheduledThreadPoolTest {

    @Test
    public void runScheduledThreadTest() throws InterruptedException {
        ExecuteScheduledThreadPool executeScheduledThreadPool = new ExecuteScheduledThreadPool();
        executeScheduledThreadPool.runTaskInPool();

    }
}
