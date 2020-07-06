package com.course.calypso.java9.streams;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Map;

public class StreamsUtilDemoTest {

    private StreamsUtilDemo streamsUtilDemo = new StreamsUtilDemo();

    @Test
    public void give_random_number_of_stream_size(){
        streamsUtilDemo.randomNumberTest();
    }

    @Test
    public void give_random_double_number_of_stream_size_bounded(){
        streamsUtilDemo.randNumberDoubleBounded();//Between 0 to 0.5
    }

    @Test
    public void give_random_int_number_of_stream_size_bounded(){
        streamsUtilDemo.randomNumberIntegerBounded();//Between 0 to 5
    }

    @Test
    public void given_fibonacci_memoization(){
        BigInteger number = streamsUtilDemo.fib(10);
        Assert.assertEquals(55,number.intValue());
    }

    @Test
    public void given_passage_return_word_count(){
        String passage = "NSA agent  NSA walks into a bar. Bartender says, " +
                "'Hey, I have a new joke for you.' Agent says, 'heard it'.";
        Map<String, Integer> counts = streamsUtilDemo.countWordsComputeIfPresent(passage,
                "NSA", "agent", "joke");
        counts.forEach((word, count) -> System.out.println(word + "=" + count));
        Assert.assertEquals(Integer.valueOf(2),counts.get("NSA"));
    }

}
