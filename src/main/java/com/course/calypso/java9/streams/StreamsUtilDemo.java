package com.course.calypso.java9.streams;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StreamsUtilDemo {

    /*
    * The overloaded versions of each allow you to specify the size of the resulting stream and the min and max values for the generated numbers. For example:
       DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound)
    * */

    public void randomNumberTest() {
        //Generating streams of random numbers
        Random random = new Random();
        random.ints(5)//(long streamSize, double randomNumberOrigin, double randomNumberBound)
                .sorted()
                .forEach(System.out::println);
      /*
        -1622707470
        -452508309
        1346762415
        1456878623
        1783692417
        */
    }

    public void randNumberDoubleBounded() {
        Random random = new Random();
        random.doubles(5, 0, 0.5)
                .sorted()
                .forEach(System.out::println);
    }

    public void randomNumberIntegerBounded() {
        Random random = new Random();
        random.ints(5, 0, 5)
                .sorted()
                .forEach(System.out::println);//0 1 2 2 3
    }

    /*
     *The way to fix this is to use a cache, a technique known as memoization in functional programming. The result,
     *  modified to store BigInteger instances
     * computeIfAbsent: Return the value for the given key if it exists, or use the supplied function to compute and
     * store it if not
     * */
    public BigInteger fib(long i) {
        Map<Long, BigInteger> cache = new HashMap<>();
        if (i == 0) return BigInteger.ZERO;
        if (i == 1) return BigInteger.ONE;

        //The complete signature for the computIfAbsent method is:
        //V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
        //This method is particularly useful when creating a cache of the results of method calls.
        return cache.computeIfAbsent(i, n -> fib(n - 2).add(fib(n - 1)));
    }

    /*
    * ComputeIfPresent : Compute a new value to replace an existing value
    * */
    public Map<String, Integer> countWordsComputeIfPresent(String passage, String... strings) {

        Map<String, Integer> wordCounts = new HashMap<>();
        Arrays.stream(strings).forEach(s -> wordCounts.put(s, 0));
        Arrays.stream(passage.split(" ")).forEach(word ->
                wordCounts.computeIfPresent(word, (key, val) -> val + 1));//word,
        return wordCounts;

    }

}
