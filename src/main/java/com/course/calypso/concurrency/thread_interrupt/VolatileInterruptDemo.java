package com.course.calypso.concurrency.thread_interrupt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VolatileInterruptDemo {

    public void runTask(){
        Task task = new Task();
        for(int index=0;index< 5;index++){
            Thread thread = new Thread(task);
            thread.start();
        }
        log.info("Interrupt thread");
        task.keepRunning = false;


    }


}

@Slf4j
class Task implements Runnable{

    public volatile boolean keepRunning = true;

    @Override
    public void run()  {
        log.info("Is running :{}",keepRunning);
        if(!keepRunning){
            log.info("Thread has been interrupted: {} ",Thread.currentThread().getName());
        }
        while(keepRunning){
            log.info("Doing some Java operation.... not I/O operation");
        }

    }
}