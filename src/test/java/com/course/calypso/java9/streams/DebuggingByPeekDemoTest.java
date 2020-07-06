package com.course.calypso.java9.streams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DebuggingByPeekDemoTest {

    private DebuggingByPeekDemo  demo = new DebuggingByPeekDemo();

    @Test
    public void sumDoublesDivisibleBy3() throws Exception {
        assertEquals(1554, demo.sumDoublesDivisibleBy3(100, 120));
    }

    @Test
    public void sumDoublesDivisibleBy3ToDebug() throws Exception {
        assertEquals(1554, demo.sumDoublesDivisibleBy3ToDebug(100, 120));
    }

    @Test
    public void sumDoublesDivisibleBy3ToDebugByPeek() throws Exception {
        assertEquals(1554, demo.sumDoublesDivisibleBy3ToDebugByPeek(
                100, 120));
    }
}
