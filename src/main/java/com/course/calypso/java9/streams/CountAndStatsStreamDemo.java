package com.course.calypso.java9.streams;


import lombok.extern.slf4j.Slf4j;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class CountAndStatsStreamDemo {

    public long countStream(int[]... args) {

        long count = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5).count();
        System.out.printf("There are %d elements in the stream %n", count);
        return count;
    }

    public Long countStreamCollect() {
        Long count = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .collect(Collectors.counting());
        System.out.printf("There are %d elements in the stream%n", count);
        return count;
    }

    public Map countUsingPartitionBy(List<String> strings) {
        // false: 4
        // true: 8
    /* Predicate
    Downstream collector
    The first argument to partitioningBy is a Predicate, used to separate the strings into two categories: those that satisfy
    the predicate, and those that do not. If that was the only argument to partitioningBy, the result would be a
    Map<Boolean, List<String>>, where the keys would be the values true and false, and the values would be lists of
    even- and odd-length strings.
    The two-argument overload of partitioningBy used here takes a Predicate followed by a Collector,
    called a downstream collector, which post processes each list of strings returned.
    This is the use case for the Collectors.counting method.
    */

        Map<Boolean, Long> numberLengthMap = strings.stream()
                .collect(Collectors.partitioningBy(
                        s -> s.length() % 2 == 0,
                        Collectors.counting()));
        numberLengthMap.forEach((k, v) -> System.out.printf("%5s: %d%n", k, v)); //
        return  numberLengthMap;

    }

    public IntSummaryStatistics summaryStatisticsStream(){

        IntSummaryStatistics stats = IntStream.rangeClosed(1,500)
                .summaryStatistics();
        System.out.println(stats);
        System.out.println("count: " + stats.getCount());
        System.out.println("min  : " + stats.getMin());
        System.out.println("max  : " + stats.getMax());
        System.out.println("sum  : " + stats.getSum());
        System.out.println("ave  : " + stats.getAverage());
        return  stats;
    }

}
