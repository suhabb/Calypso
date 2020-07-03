package com.course.calypso.concurrency.thread_util;

import com.course.calypso.service.BarrierTask;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {


    public void runCyclicBarrierTask() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        executorService.execute(new BarrierTask(cyclicBarrier));
        executorService.execute(new BarrierTask(cyclicBarrier));
        executorService.execute(new BarrierTask(cyclicBarrier));
        Thread.sleep(3000);

    }
}
