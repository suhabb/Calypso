package com.course.calypso.concurrency.thread_interrupt;

import com.course.calypso.service.CalculateTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadInterruptDemo implements Runnable {

    @Override
    public void run() {
        boolean isInterrupted = Thread.currentThread().isInterrupted();
        log.info("Is thread Interrupted:{}",isInterrupted);
        while(!isInterrupted) {
            log.info(" New Thread is running : {}", Thread.currentThread().getName());
            try {
                writeFileToDb();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeFileToDb() {

        log.info("Writing file to db using thread : {}", Thread.currentThread().getName());
    }
}
