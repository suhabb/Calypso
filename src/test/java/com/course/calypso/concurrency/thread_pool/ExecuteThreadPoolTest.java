package com.course.calypso.concurrency.thread_pool;

import com.course.calypso.concurrency.handler.RejectionCustomHandler;
import com.course.calypso.service.CalculateTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ExecuteThreadPoolTest {

    @Test
    public void runThreadPoolTask() throws InterruptedException {
        ExecuteThreadPool executeThreadPool = new ExecuteThreadPool();
        executeThreadPool.runTaskInPool();
    }
}
