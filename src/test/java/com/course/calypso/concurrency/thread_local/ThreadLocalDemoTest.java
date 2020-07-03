package com.course.calypso.concurrency.thread_local;

import org.junit.Test;

public class ThreadLocalDemoTest {

    @Test
    public void testThreadLocal() throws InterruptedException {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        threadLocalDemo.execute();
    }
}
