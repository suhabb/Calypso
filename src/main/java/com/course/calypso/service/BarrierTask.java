package com.course.calypso.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Slf4j
public class BarrierTask implements Runnable {

    CyclicBarrier cyclicBarrier ;

    public BarrierTask(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        try {
            log.info(" Waiting for all threads to come to barrier : {}", Thread.currentThread().getName()) ;
            this.cyclicBarrier.await();
            log.info(" All the thread are in Barrier : {}", Thread.currentThread().getName()) ;
            writeFileToDb();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void writeFileToDb() {

        log.info("Writing file to db using thread : {}", Thread.currentThread().getName());
    }
}
