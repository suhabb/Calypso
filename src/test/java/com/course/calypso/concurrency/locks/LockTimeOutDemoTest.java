package com.course.calypso.concurrency.locks;

import com.course.calypso.concurrency.thread_local.ThreadLocalDemo;
import org.junit.Test;

public class LockTimeOutDemoTest {

    @Test
    public void testTimeoutLock() throws InterruptedException {

        LockTimeoutThread t1 = new LockTimeoutThread(2);
        LockTimeoutThread t2 = new LockTimeoutThread(4);
        LockTimeoutThread t3 = new LockTimeoutThread(3);
        LockTimeoutThread t4 = new LockTimeoutThread(6);

        //run multiple threads
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
    }
}
