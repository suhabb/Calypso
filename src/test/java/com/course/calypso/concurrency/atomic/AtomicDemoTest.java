package com.course.calypso.concurrency.atomic;

import org.junit.Test;

public class AtomicDemoTest {

    @Test
    public void testAtomic() throws InterruptedException {
        AtomicDemo atomicDemo = new AtomicDemo();
        atomicDemo.atomicInteger();
    }
}
