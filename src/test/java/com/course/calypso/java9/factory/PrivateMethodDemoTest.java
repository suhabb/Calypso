package com.course.calypso.java9.factory;

import org.junit.Assert;
import org.junit.Test;

public class PrivateMethodDemoTest {

    private SumNumbers demo = new PrivateMethodDemo();

    @Test
    public void addEvens() {
        Assert.assertEquals(2 + 4 + 6, demo.addEvens(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void addOdds()  {
        Assert.assertEquals(1 + 3 + 5, demo.addOdds(1, 2, 3, 4, 5, 6));
    }
}