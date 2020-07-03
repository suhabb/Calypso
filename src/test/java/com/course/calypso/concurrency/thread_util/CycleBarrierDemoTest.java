package com.course.calypso.concurrency.thread_util;

import org.junit.Test;

public class CycleBarrierDemoTest {

    @Test
    public void testCycleBarrier() throws InterruptedException {
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        cyclicBarrierDemo.runCyclicBarrierTask();
    }
}
