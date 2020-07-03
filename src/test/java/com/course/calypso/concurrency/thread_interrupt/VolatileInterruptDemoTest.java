package com.course.calypso.concurrency.thread_interrupt;

import org.junit.Test;

public class VolatileInterruptDemoTest {

    @Test
    public void testVolatileInterrupt(){
        VolatileInterruptDemo volatileInterruptDemo = new VolatileInterruptDemo();
        volatileInterruptDemo.runTask();
    }
}
