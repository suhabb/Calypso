package com.course.calypso.concurrency.thread_interrupt;

import com.course.calypso.service.CalculateTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadInterruptDemoTest {

    @Test
    public void testInterruptThread() throws InterruptedException {

        Thread t1= new Thread(new ThreadInterruptDemo());
        Thread t2 = new Thread(new ThreadInterruptDemo());
        Thread t3 = new Thread(new ThreadInterruptDemo());
        Thread t4 = new Thread(new ThreadInterruptDemo());
        t1.start();
        t2.start();
        t3.start();
        log.info("Interrupt the thread");
        t3.interrupt();  // interrupt the thread
        Thread.sleep(2000);
        t4.start();
    }

    @Test
    public void testThreadInterruptPool() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int index=0;index<5;index++) {
            executorService.submit(new ThreadInterruptDemo());
        }
        Thread.sleep(3000);
        executorService.shutdown();
    }


}
