package com.course.calypso.java9.functional;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionDemoTest {

    private FunctionDemo functionDemo = new FunctionDemo();

    @Test
    public void given_Param_Multiply_By_10() {
        Double aDouble = functionDemo.functionToMultiplyBy10(24);
        assertEquals(Double.valueOf(240.0), aDouble);

    }

    @Test
    public void given_string_parse_integer() {
        Integer number = functionDemo.functionToParse("2");
        assertEquals(Integer.valueOf(2), Integer.valueOf(number));
    }

    @Test
    public void given_string_integer_return_double_bi_function() {

        Double number = functionDemo.biFunctional("10",5);
        assertEquals(Double.valueOf(4.0), Double.valueOf(number));
    }
}
