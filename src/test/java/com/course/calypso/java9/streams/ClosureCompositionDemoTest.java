package com.course.calypso.java9.streams;


import org.junit.Assert;
import org.junit.Test;

/*
* You want to apply a series of small, independent functions consecutively.

Use the composition methods defined as defaults in the Function, Consumer, and Predicate interfaces.
*
* */
public class ClosureCompositionDemoTest {

    private ClosureCompositionDemo closureCompositionDemo = new ClosureCompositionDemo();

    @Test
    public void given_integer_apply_compose_function() {
        int actual = closureCompositionDemo.composeFunction(2);
        Assert.assertEquals(8,actual);
    }

    @Test
    public void given_integer_apply_and_then_function() {
        int actual = closureCompositionDemo.andThenFunction(2);
        Assert.assertEquals(12,actual);
    }
}
