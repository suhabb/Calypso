package com.course.calypso.concurrency.queue;

import org.junit.Test;

public class BlockingQueueDemoTest {

    @Test
    public void runArrayBlockingQueue() throws InterruptedException {
        BlockingQueueDemo blockingQueueDemo = new BlockingQueueDemo();
        blockingQueueDemo.runArrayBlockingQueue();
    }

    @Test
    public void runLinkedBlockingQueue() throws InterruptedException {
        BlockingQueueDemo blockingQueueDemo = new BlockingQueueDemo();
        blockingQueueDemo.runLinkedBlockingQueue();
    }

    /*
     The SynchronousQueue is a queue that can only contain a single element internally.
     A thread inserting an element into the queue is blocked until another thread takes that
     element from the queue. Likewise, if a thread tries to take an element and no element
     is currently present, that thread is blocked until a thread insert an element into the queue.
    }*/
    @Test
    public void runSynchronousQueue() throws InterruptedException {
        BlockingQueueDemo blockingQueueDemo = new BlockingQueueDemo();
        blockingQueueDemo.runSynchronousQueue();
    }
}
