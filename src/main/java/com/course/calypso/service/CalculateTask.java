package com.course.calypso.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculateTask implements Runnable {

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
            log.info(" New Thread is running : {}", Thread.currentThread().getName()) ;
            try {
                writeFileToDb();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    private void writeFileToDb() {

        log.info("Writing file to db using thread : {}", Thread.currentThread().getName());
    }
}
