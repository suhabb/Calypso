package com.course.calypso.concurrency.semaphore;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreDemoTest {

    // creating a Semaphore object
    // with number of permits 1

    @Test
    public void testSemaphorePermits() throws InterruptedException {

        Semaphore sem = new Semaphore(1,true);//fair: thread waiting for longest time  will have permit

        // creating two threads with name A and B
        // Note that thread A will increment the count
        // and thread B will decrement the count
        SemaphoreDemo mt1 = new SemaphoreDemo(sem, "A");
        SemaphoreDemo mt2 = new SemaphoreDemo(sem, "B");


        // stating threads A and B
        mt1.start();
        mt2.start();

        // waiting for threads A and B
        mt1.join();
        mt2.join();

        // count will always remain 0 after
        // both threads will complete their execution
        log.info("count:{} " + Shared.count);

    }
}
