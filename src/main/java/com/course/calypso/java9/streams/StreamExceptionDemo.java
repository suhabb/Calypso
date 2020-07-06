package com.course.calypso.java9.streams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
* Problem
You have a lambda expression that throws a checked exception, and the abstract method in the functional interface you are implementing does not declare that excep‐ tion.
Solution
Add a try/catch block to the lambda expression, or delegate to an extracted method to handle it.
* */
public class StreamExceptionDemo {

    public List<String> encodeValues(String... values) {
        return Arrays.stream(values)
                .map(s -> {
                    try {
                        return URLEncoder.encode(s, "UTF-8");
                    } catch (UnsupportedEncodingException e) { //add method signature
                       throw new RuntimeException();
                    }
                })
                .collect(Collectors.toList());
    }


    public List<String> encodeValuesUsingMethod(String... values) {

        return Arrays.stream(values)
                .map(this::encodeStringFunction) //call the method of the same class
                .collect(Collectors.toList());
    }

    private String encodeStringFunction(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
