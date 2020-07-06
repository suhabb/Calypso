package com.course.calypso.java9.streams;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StreamExceptionDemoTest {

    private StreamExceptionDemo streamExceptionDemo = new StreamExceptionDemo();


    @Test()
    public void given_input_return_encoded_string(){
        List<String> actual = streamExceptionDemo.encodeValues("Steve Jobs");
        Assert.assertEquals("Steve+Jobs",actual.get(0));
    }

    @Test(expected = NullPointerException.class)
    public void given_input_throw_exception(){
        streamExceptionDemo.encodeValues(null);
    }

    @Test(expected = NullPointerException.class)
    public void given_input_throw_exception_using_method(){
        streamExceptionDemo.encodeValuesUsingMethod(null);
    }
}
