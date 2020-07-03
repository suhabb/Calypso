package com.course.calypso.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class ConsumerTask implements Runnable{

    protected BlockingQueue queue = null;

    public ConsumerTask(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            log.info("Take {}",queue.take());
            log.info("Take {}",queue.take());
            log.info("Take {}",queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}