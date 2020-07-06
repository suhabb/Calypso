package com.course.calypso.java9.streams;

import com.course.calypso.dto.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;

@Slf4j
public class ReduceDemoTest {

    private ReduceDemo reduceDemo = new ReduceDemo();

    //String[] strings = "this is an array of strings".
    //this,  is,       an ,   array,  of,    strings
    //a[0]    a[1]     a[2]   a[3]   a[4]    a[5]
    //total length - 6

    @Test
    public void given_String_return_reduce_count(){

        String strings = "this is an array of strings";//6 words
        long reduceCount = reduceDemo.reduceCount();
        Assert.assertEquals(6,reduceCount);
    }

    @Test
    public void given_String_return_reduce_sum(){

        String strings = "this is an array of strings";
        long reduceSum = reduceDemo.reduceSum(strings);
        Assert.assertEquals(22,reduceSum);
    }

    @Test
    public void given_String_return_reduce_average(){

        String strings = "this is an array of strings";
        OptionalDouble reduceAvg = reduceDemo.reduceAverage(strings);
        log.info("Reduce Average:{}",reduceAvg);
    }

    @Test
    public void given_String_return_reduce_max(){

        String strings = "this is an array of strings";
        OptionalInt reduceMax = reduceDemo.reduceMax(strings);
        Assert.assertSame(7,reduceMax.getAsInt());//strings
    }

    @Test
    public void given_String_return_reduce_min(){

        String strings = "this is an array a of strings";
        OptionalInt reduceMin = reduceDemo.reduceMin(strings);
        Assert.assertSame(1,reduceMin.getAsInt());//a
    }

    @Test
    public void given_String_return_reduce_range(){

        int sum = reduceDemo.reduceRange();
        Assert.assertSame(55,sum);
    }

    @Test
    public void given_String_return_reduce_range_sum(){

        int sum = reduceDemo.reduceRangeSum();
        Assert.assertSame(109,sum);
    }

    @Test
    public void given_String_return_reduce_range_to_sum(){

        int sum = reduceDemo.reduceRangeToSum();
        Assert.assertSame(55,sum);
    }

    @Test
    public void given_String_return_reduce_range_to_max(){

        int max = reduceDemo.reduceMax();
        Assert.assertSame(9,max);
    }

    @Test
    public void given_String_return_reduce_range_to_concat(){

        String concat = reduceDemo.reduceConcat();
        Assert.assertEquals("thisisalist", concat);
    }

    @Test
    public void given_String_return_reduce_range_to_concat_(){

        String concat = reduceDemo.concat();
        Assert.assertEquals("thisisalist", concat);
    }

    @Test
    public void given_list_return_reduce_to_map(){
        List<Book> bookList = Arrays.asList(Book.builder().id(1).name("Steve Jobs").build(),
                Book.builder().id(2).name("Henry").build());
        Map<Integer, Book> bookMap = reduceDemo.reduceMap(bookList);
        Assert.assertEquals("Henry",bookMap.get(2).getName());
    }

    @Test
    public void given_test_Sort_reduce_by_length(){
        List<String> strings = Arrays.asList(
                "this", "is", "a", "list", "of", "strings");
        List<String> stringList = reduceDemo.reduceSortStringByLength(strings);
        Assert.assertEquals("a",stringList.get(0));

    }
}
