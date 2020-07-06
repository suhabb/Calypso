package com.course.calypso.java9.streams;

import java.util.stream.IntStream;

public class DebuggingByPeekDemo {


    public int sumDoublesDivisibleBy3(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .map(n -> n * 2).filter(n -> n % 3 == 0).sum();
    }

    public int sumDoublesDivisibleBy3ToDebug(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .map(n -> {
                    System.out.println(n);
                    return n;
                })
                .map(n -> n * 2).filter(n -> n % 3 == 0).sum();
    }
    /*
    * The declaration of the peek method is: Stream<T> peek(Consumer<? super T> action) According to the Javadocs,
    * the peek method “returns a stream consisting of the ele‐ ments of this stream, additionally performing the provided
    *  action on each element as they are consumed from the resulting stream.” Recall that a Consumer takes a single
    * input but returns nothing, so any provided Consumer will not corrupt each value as it streams by.Since peek is an
    * intermediate operation, the peek method can be added multiple times if you wish
    * */
    public int sumDoublesDivisibleBy3ToDebugByPeek(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .peek(n -> System.out.printf("original: %d%n", n))
                .map(n -> n * 2)
                .peek(n -> System.out.printf("doubled : %d%n", n))
                .filter(n -> n % 3 == 0)
                .peek(n -> System.out.printf("filtered: %d%n", n))
                .sum();
    }


}
