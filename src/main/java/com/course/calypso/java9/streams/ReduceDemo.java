package com.course.calypso.java9.streams;

import com.course.calypso.dto.Book;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
public class ReduceDemo {

    /*
        * Solution
        Use the reduce method to accumulate calculations on each element. Discussion
        The functional paradigm in Java often uses a process known as map-filter-reduce.
        * The map operation transforms a stream of one type (like a String) into another (like an int, by invoking the
        * length method). Then a filter is applied to produce a new stream with only the desired elements in
        * it (e.g., strings with length below a certain threshold). Finally, you may wish to provide a terminal operation
        * that generates a sin‐ gle value from the stream (like a sum or average of the lengths).
  */
    public long reduceCount() {

        String[] strings = "this is an array of strings".split(" ");
        long count = Arrays.stream(strings)
                .map(String::length)//The map operation transforms a stream of one type (like a String) into another
                // (like an int, by invoking the length method)
                .count();
        log.info("There are " + count + " strings");
        return count;
    }

    public int reduceSum(String arg) {

        //String[] strings = "this is an array of strings".split(" ");
        String[] strings = arg.split(" ");
        int totalLength = Arrays.stream(strings)
                .mapToInt(String::length) // total the length of each word in the array
                .sum();
        log.info("The total length is " + totalLength);
        return totalLength;

    }

    public OptionalDouble reduceAverage(String arg) {
        //      String[] strings = "this is an array of strings".split(" ");
        String[] strings = arg.split(" ");
        OptionalDouble ave = Arrays.stream(strings)
                .mapToInt(String::length)
                .average();
        log.info("The average length is " + ave);
        return ave;

    }

    public OptionalInt reduceMax(String arg) {
        //String[] strings = "this is an array of strings".split(" ");
        String[] strings = arg.split(" ");
        OptionalInt max = Arrays.stream(strings)
                .mapToInt(String::length)
                .max();
        log.info("The average length is " + max);
        return max;
    }

    public OptionalInt reduceMin(String arg) {
        //String[] strings = "this is an array of strings".split(" ");
        String[] strings = arg.split(" ");
        OptionalInt min = Arrays.stream(strings)
                .mapToInt(String::length)
                .min();
        log.info("The min lengths are : " + min);
        return min;
    }

    /*
     * In the lambda expression, you can think of the first argument of the binary operator as an accumulator,
     * and the second argument as the value of each element in the stream. This is made clear if you print each
     * one as it goes by, as shown in
     *
     * */
    public int reduceRange() {
        //x: as an accumulator
        //y: value of each element
        int sum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> {
                    System.out.printf("x=%d, y=%d%n", x, y);
                    return x + y;
                }).orElse(0);
        return sum;
    }

    public int reduceRangeSum() {

        int doubleSum = IntStream
                .rangeClosed(1, 10)
                .reduce((x, y) -> x + 2 * y)
                .orElse(0);
        return doubleSum;
    }

    public int reduceRangeToSum() {

        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .reduce(0, Integer::sum);
        log.info("Sum:{}", sum);
        return sum;
    }

    public int reduceMax() {
        Integer max = Stream.of(3, 1, 4, 1, 5, 9)
                .reduce(Integer.MIN_VALUE, Integer::max);
        log.info("The max value is " + max);
        return max;
    }

    public String reduceConcat() {
        String s = Stream.of("this", "is", "a", "list")
                .reduce("", String::concat);
        log.info("Concat: {}", s);
        return s;
    }

    public String concat() {
        //better performance
        String s = Stream.of("this", "is", "a", "list").collect(StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append)
                .toString();
        return s;
    }

    public Map<Integer, Book> reduceMap(List<Book> books) {

        HashMap<Integer, Book> bookMap = books.stream()
                .reduce(new HashMap<Integer, Book>(), //  Identity value for putAll

                        (map, book) -> {//Accumulate a single book into Map using put
                            //The second argument is a function that adds a single book to a Map. This too would
                            // be simpler if the put method on Map returned the Map after the new entry was added.
                            map.put(book.getId(), book);
                            return map;
                        },
                        (map1, map2) -> {                  //Combine multiple Maps using putAll
                            //The last argument is a combiner, which is required to be a BinaryOperator. In this case,
                            // the provided lambda expression takes two maps and copies all the keys from the second map
                            // into the first one and returns it. The lambda expression would be simpler if the putAll
                            // method returned the map, but no such luck. The combiner is only relevant if the reduce
                            // operation is done in parallel,
                            // because then you need to combine maps produced from each portion of the range.
                            map1.putAll(map2);
                            return map1;
                        });
        bookMap.forEach((k, v) -> System.out.println(k + ": " + v));
        return bookMap;
    }

    public List<String> reduceSortStringByLength(List<String> strings){

        List<String> sorted = strings.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(toList());
        return sorted;
    }
}
