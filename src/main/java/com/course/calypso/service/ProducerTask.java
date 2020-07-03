package com.course.calypso.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class ProducerTask implements Runnable {

    protected BlockingQueue queue = null;

    public ProducerTask(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            //add the objects in the queue
            log.info("Added object 1");
            queue.put("1");
            Thread.sleep(1000);
            log.info("Added object 2");
            queue.put("2");
            Thread.sleep(1000);
            log.info("Added object 3");
            queue.put("3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}