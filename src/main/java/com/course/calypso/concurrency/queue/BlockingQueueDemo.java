package com.course.calypso.concurrency.queue;

import com.course.calypso.service.ConsumerTask;
import com.course.calypso.service.ProducerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

@Slf4j
public class BlockingQueueDemo {

    public void runArrayBlockingQueue() throws InterruptedException {

        BlockingQueue queue = new ArrayBlockingQueue(1024);

        ProducerTask producerTask = new ProducerTask(queue);
        ConsumerTask consumerTask = new ConsumerTask(queue);

        new Thread(producerTask).start();
        new Thread(consumerTask).start();

        Thread.sleep(4000);
    }


    public void runLinkedBlockingQueue() throws InterruptedException {

        BlockingQueue<String> unbounded = new LinkedBlockingQueue<String>();
        BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);

        ProducerTask producerTask = new ProducerTask(unbounded);
        ConsumerTask consumerTask = new ConsumerTask(unbounded);


        new Thread(producerTask).start();
        new Thread(consumerTask).start();

        Thread.sleep(4000);
    }

    public void runSynchronousQueue() throws InterruptedException {

        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();

        ProducerTask producerTask = new ProducerTask(synchronousQueue);
        ConsumerTask consumerTask = new ConsumerTask(synchronousQueue);


        new Thread(producerTask).start();
        new Thread(consumerTask).start();

        Thread.sleep(4000);
    }
}